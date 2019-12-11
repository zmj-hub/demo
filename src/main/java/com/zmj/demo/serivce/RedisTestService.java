package com.zmj.demo.serivce;

import com.zmj.demo.bean.User;
import com.zmj.demo.dao.UserDao;
import com.zmj.demo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RedisTestService {
    @Resource
    UserDao userDao;

    @Autowired
    RedisUtils redisUtils;

    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    public User getUserById(Long id) {
        String key = "user_" + id;
        if (redisUtils.exists(key)) {
            User user = (User) redisUtils.get(key);
            return user;
        } else {
            User user = userDao.getUserById(id);
            redisUtils.set(key, user, 5L, TimeUnit.HOURS);
            return user;
        }
    }

    public User addUser(User user) {
        userDao.addUser(user);
        String key = "user_" + user.getUserId();
        redisUtils.set(key, user, 1L, TimeUnit.MINUTES);
        return user;
    }

    public User updateUser(User user) {
        userDao.updateUser(user);
        String key = "user_" + user.getUserId();
        if (redisUtils.exists(key)) {
            redisUtils.remove(key);
            redisUtils.set(key, user, 5L, TimeUnit.HOURS);
        } else {
            redisUtils.set(key, user, 5L, TimeUnit.HOURS);
        }
        return user;
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
        String key = "user_" + id;
        if (redisUtils.exists(key)) {
            redisUtils.remove(key);
        }
//        HashMap<String, String> hash = new HashMap<> ();
        ConcurrentHashMap<String, String> concurrent = new ConcurrentHashMap<>();
    }
}
