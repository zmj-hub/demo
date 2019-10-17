package com.zmj.demo.serivce;

import com.zmj.demo.bean.UserBean;
import com.zmj.demo.dao.UserDao;
import com.zmj.demo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RedisTestService {
    @Resource
    UserDao userDao;

    @Autowired
    RedisUtils redisUtils;

    public List<UserBean> findAllUser() {
        return userDao.findAllUser();
    }

    public UserBean getUserById(Long id) {
        String key = "user_" + id;
        if (redisUtils.exists(key)) {
            UserBean userBean = (UserBean) redisUtils.get(key);
            return userBean;
        } else {
            UserBean userBean = userDao.getUserById(id);
            redisUtils.set(key, userBean,5L,TimeUnit.HOURS);
            return userBean;
        }
    }

    public UserBean addUser(UserBean userBean) {
        userDao.addUser(userBean);
        String key = "user_" + userBean.getUserId();
        redisUtils.set(key, userBean,1L, TimeUnit.MINUTES);
        return userBean;
    }

    public UserBean updateUser(UserBean userBean) {
        userDao.updateUser(userBean);
        String key = "user_" + userBean.getUserId();
        if (redisUtils.exists(key)) {
            redisUtils.remove(key);
            redisUtils.set(key, userBean,5L,TimeUnit.HOURS);
        } else {
            redisUtils.set(key, userBean,5L,TimeUnit.HOURS);
        }
        return userBean;
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
        String key = "user_" + id;
        if (redisUtils.exists(key)) {
            redisUtils.remove(key);
        }
    }
}
