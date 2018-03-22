package com.iacteam2.webshop.security;

import com.iacteam2.webshop.exception.SessionTimeoutException;
import com.iacteam2.webshop.model.User;
import com.iacteam2.webshop.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by dahir on Thu 22-03-18.
 */
@Component
public class Jwt {
    private Resource resource = new ClassPathResource("/application.properties");
    private Properties props = PropertiesLoaderUtils.loadProperties(resource);
        private final byte[] key = props.getProperty("JWTKey").getBytes("UTF-8");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public Jwt() throws IOException {
    }
    public String generateJWT(User user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(now)
                .claim("distortion", nowMillis)
                .claim("userId", user.getId())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public User parseJWT(String token) throws UnsupportedEncodingException {

        Jws<Claims> claims = Jwts.parser()
                .requireSubject("user")
                .setSigningKey(key)
                .parseClaimsJws(token);

        Integer id = claims.getBody().get("userId", Integer.class);
        Long userId = id.longValue();

        return userRepository.findById(userId).orElseThrow(() -> new SessionTimeoutException("session expired"));
    }
}
