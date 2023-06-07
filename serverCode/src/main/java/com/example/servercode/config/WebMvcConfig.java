package com.example.servercode.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//在resources文件夹下的backend和front文件内资源不可以直接访问，所以使用该映射来访问
@Slf4j
@Configuration
//WebMvcConfigurationSupport 类提供了一个扩展点，用于自定义和配置 Spring MVC 的行为和设置
public class WebMvcConfig extends WebMvcConfigurationSupport {
    protected void addResourceHandlers(ResourceHandlerRegistry registry){
        log.info("开始进行静态资源映射");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}
