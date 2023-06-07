package com.example.servercode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.servercode.common.R;
import com.example.servercode.entity.Employee;
import com.example.servercode.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*
    * 员工登陆
    * */
    @PostMapping("/login")
    //@RequestBody:将json数据封装成对象
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //将密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //如果没有查询到则返回登陆失败
        if (emp == null){
            return R.error("登陆失败");
        }
        //密码比对,如果不一致则返回登陆失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        //查看用户状态,如果已被禁用,则返回用户已禁用结果
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }
        //登陆成功,将员工id存入Session并返回登陆成功
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /*
    * 员工退出
    * */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中报错的当前登陆员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
