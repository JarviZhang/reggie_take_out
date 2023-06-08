package com.example.servercode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.servercode.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
