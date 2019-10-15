package com.zmj.demo.dao;

import com.zmj.demo.bean.UserBean;

public interface UserDao {

    UserBean getUserById(Long id);
    UserBean getUserByName(String userName);

}
