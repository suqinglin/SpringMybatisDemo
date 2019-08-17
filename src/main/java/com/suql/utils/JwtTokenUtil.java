package com.suql.utils;

import com.suql.service.RedisService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.LocalDate;

import java.util.Date;

public class JwtTokenUtil {

    /**
     * 生成jwt
     * @param userId
     * @return
     */
    public static String generateToken(Long userId) {
        final Date expirationDate = LocalDate.now().plusMonths(1).toDate();
        final Date createdDate = LocalDate.now().toDate();
        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(expirationDate)
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS256, "secret_key")
                .compact();
    }

    public static String createToken(Long userId, RedisService redisService) {
        String token = generateToken(userId);
        redisService.set("springmybatisdemo:token:" + userId, token);
        return token;
    }
}
