package com.zmj.demo.dao;

import com.zmj.demo.bean.UserBean;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.List;

public interface UserDao {

    UserBean getUserById(Long id);
    UserBean getUserByName(String userName);

    List<UserBean> findAllUser();
    Long addUser(UserBean userBean);
    void deleteUser(Long id);
    UserBean update(UserBean userBean);

}
