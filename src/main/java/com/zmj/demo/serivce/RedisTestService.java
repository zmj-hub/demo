package com.zmj.demo.serivce;

import com.zmj.demo.bean.UserBean;
import com.zmj.demo.dao.UserDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisTestService {
    @Resource
    UserDao userDao;

    @Cacheable(value = "user", key = "123")
    public List<UserBean> findAllUser(){
        return userDao.findAllUser();
    }
}
