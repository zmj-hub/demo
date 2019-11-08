package com.zmj.demo.controller;

import com.zmj.demo.bean.UserBean;
import com.zmj.demo.serivce.RedisTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("redisTest")
public class RedisTestController {
    @Autowired
    RedisTestService redisTestService;

    @ApiOperation(value = "查询所有人员", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public Object findAllUser(HttpServletRequest request) {
        return redisTestService.findAllUser();
    }

    @ApiOperation(value = "根据id查询用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public Object getUserById(HttpServletRequest request, @RequestParam Long id) {
        return redisTestService.getUserById(id);
    }

    @ApiOperation(value = "新增用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request, @RequestBody UserBean userBean) {
        return redisTestService.addUser(userBean);
    }

    @ApiOperation(value = "更新用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request, @RequestBody UserBean userBean) {
        return redisTestService.updateUser(userBean);
    }

    @ApiOperation(value = "删除用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Object deleteUser(HttpServletRequest request, @RequestBody Long id) {
        redisTestService.deleteUser(id);
        return id;
    }
}
