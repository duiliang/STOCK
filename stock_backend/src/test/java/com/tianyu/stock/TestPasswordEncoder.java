package com.tianyu.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testPasswordEncoder() {
        System.out.println("test password encoder");
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        System.out.println(encode);
    }
    @Test
    public void testPasswordEncoder2() {
        System.out.println("test password encoder2");
        String password = "123456";
        String encode = passwordEncoder.encode(password);
        System.out.println(passwordEncoder.matches(password,encode)? "true": "false");
    }
}
