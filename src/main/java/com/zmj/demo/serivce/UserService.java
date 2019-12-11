package com.zmj.demo.serivce;


import com.zmj.demo.bean.User;
import com.zmj.demo.dao.LogTokenDao;
import com.zmj.demo.dao.UserDao;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService {
    Random random = new Random();
    @Resource
    UserDao userDao;
    @Resource
    LogTokenDao logTokenDao;


    @Cacheable(value = "user",key = "#id")
    public User getUserById(Long id) {
        User user= userDao.getUserById(id);
        return user;
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    //public Map<String,String> register(String userName,String userPassword){
//
//    Map<String,String> map = new HashMap<String, String>();
//    if (StringUtils.isEmpty(userName)){
//        map.put("msg","用户名不能为空");
//        return map;
//    }
//    if (StringUtils.isEmpty(userPassword)){
//        map.put("msg","密码不能为空");
//        return map;
//    }
//    User user = userDao.getUserByName(userName);
//    if(user != null){
//        map.put("msg","用户名已存在");
//        return map;
//    }
//    user =new User();
//    user.setUserName(userName);
//    user.setSalt(UUID.randomUUID().toString().substring(0,5));
//    user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
//    user.setPassword(userPassword);
//
//
//    return null;
//}
    @Cacheable(value = "user", key = "123")
//@Cacheable(cacheNames = {"hello"})
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }


}
