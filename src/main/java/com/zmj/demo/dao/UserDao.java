package com.zmj.demo.dao;

import com.zmj.demo.bean.User;

import java.util.List;

public interface UserDao {

    User getUserById(Long id);

    User getUserByName(String userName);

    List<User> findAllUser();

    void addUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);

}
