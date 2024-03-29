package com.zmj.demo.serivce;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zmj.demo.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TokenService {

    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user.getUserId().toString()).withIssuedAt(start).withIssuedAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
