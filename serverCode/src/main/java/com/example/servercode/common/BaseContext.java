package com.example.servercode.common;
/*
* 基于ThreadLocal封装工具类，用户保存和获取当前用户的id
* */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
