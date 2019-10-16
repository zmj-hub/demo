package com.zmj.demo.controller;

import com.zmj.demo.serivce.RedisTestService;
import com.zmj.demo.serivce.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("redisTest")
public class RedisTestController {
    @Autowired
    RedisTestService redisTestService;

    @ApiOperation(value = "查询所有人员", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findAllUser", method = RequestMethod.POST)
    public Object findAllUser(HttpServletRequest request) {
        return redisTestService.findAllUser();
    }
}