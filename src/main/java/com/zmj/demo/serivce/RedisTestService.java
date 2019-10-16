package com.zmj.demo.serivce;

import com.zmj.demo.bean.UserBean;
import com.zmj.demo.dao.UserDao;
import com.zmj.demo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RedisTestService {
    @Resource
    UserDao userDao;

    @Autowired
    RedisUtils redisUtils;

//    @Cacheable(value = "user", key = "123")
    public List<UserBean> findAllUser() {
        List<UserBean> userBeanList;
        List<UserBean> userBeanList1=(List<UserBean>) redisUtils.get("user");
        if (userBeanList1!=null){
            userBeanList=userBeanList1;
        }else {
            userBeanList = userDao.findAllUser();
            redisUtils.set("user", userBeanList);
        }
        return userBeanList;
    }
}
