package com.zmj.demo.serivce;


import com.zmj.demo.bean.UserBean;
import com.zmj.demo.dao.LogTokenDao;
import com.zmj.demo.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService {
    Random random =new Random();
    @Resource
    UserDao userDao;
    @Resource
    LogTokenDao logTokenDao;

public UserBean getUserById(Long id){
    return userDao.getUserById(id);
}
public  UserBean getUserByName(String userName){
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
//    UserBean user = userDao.getUserByName(userName);
//    if(user != null){
//        map.put("msg","用户名已存在");
//        return map;
//    }
//    user =new UserBean();
//    user.setUserName(userName);
//    user.setSalt(UUID.randomUUID().toString().substring(0,5));
//    user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
//    user.setPassword(userPassword);
//
//
//    return null;
//}

public List<UserBean> findAllUser(){
   return userDao.findAllUser();
}



}
