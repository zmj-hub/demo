package com.zmj.demo.controller;

import com.zmj.demo.bean.UserBean;
import com.zmj.demo.serivce.TokenService;
import com.zmj.demo.serivce.UserService;
import com.zmj.demo.util.TokenUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;


    @ApiOperation(value = "通过Id获取用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "int", required = true, paramType = "form")})
    @RequestMapping("getUserById")
    public Object getUserById(Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(UserBean user, HttpServletResponse response) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        UserBean userForBase = new UserBean();
        userForBase.setUserId(userService.getUserByName(user.getUserName()).getUserId());
        userForBase.setUserName(userService.getUserByName(user.getUserName()).getUserName());
        userForBase.setPassword(userService.getUserByName(user.getUserName()).getPassword());
        if (!userForBase.getPassword().equals(user.getPassword())) {
            jsonObject.put("message", "登陆失败，密码错误");
            return jsonObject;
        } else {
            //下发token
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);
            //把token放入COOKIE中
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return jsonObject;
        }
    }

    //@UserLoginToken
    @ApiOperation(value = "获取信息", notes = "获取信息")
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());

        return "您已通过验证";
    }
}
