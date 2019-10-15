package com.zmj.demo.dao;

import com.zmj.demo.bean.UserBean;

import java.util.List;

public interface UserDao {

    UserBean getUserById(Long id);
    UserBean getUserByName(String userName);

    List<UserBean> findAllUser();

}
