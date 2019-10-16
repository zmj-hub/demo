package com.zmj.demo.controller;


import com.zmj.demo.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;
    @RequestMapping("/hello")
//    @Cacheable(cacheNames = "hello",key = "111")
    public Object hello(){
//        System.out.println("hello world");
        return userService.findAllUser();
    }
}
