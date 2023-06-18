package com.example.servercode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.servercode.common.R;
import com.example.servercode.dto.DishDto;
import com.example.servercode.entity.Category;
import com.example.servercode.entity.Dish;
import com.example.servercode.entity.DishFlavor;
import com.example.servercode.service.CategoryService;
import com.example.servercode.service.DishFlavorService;
import com.example.servercode.service.DishService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
* 菜品管理
* */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    /*
    * 新增菜品
    * */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("DishDto:{}",dishDto);
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    /*
    * 菜品分页查询
    * */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //查询菜品信息
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,queryWrapper);
        //包含菜品信息和菜品分类名称的返回对象
        Page<DishDto> dishDtoPage = new Page<>();
        //保存其他信息
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");
        //获取菜品分类名称
        List<Dish> records= pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());
        //保存菜品信息和菜品分类名称
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /*
    * 根据id查询菜品信息和对应的口味信息
    * */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("菜品更新成功");
    }

    /*
    * 根据条件查询对应的菜品数据
    * return DishDto的list
    * DishDto中有dish,dish对应的category的name,dish对应的口味dishFlavor
    * */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        //查询dish
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        //查询状态为1,即起售状态
        queryWrapper.eq(Dish::getStatus,1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            //查询dish对应的口味
            //当前菜品的id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> dishFlavorQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorQueryWrapper.eq(DishFlavor::getDishId,dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorQueryWrapper);
            dishDto.setFlavors(dishFlavorList);
            return dishDto;
        }).collect(Collectors.toList());
        return R.success(dishDtoList);
    }
}
