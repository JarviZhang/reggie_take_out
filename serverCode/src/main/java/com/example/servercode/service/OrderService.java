package com.example.servercode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.servercode.entity.Orders;

public interface OrderService extends IService<Orders> {
    /*
    * 用户下单
    * */
    public void submit(Orders orders);
}
