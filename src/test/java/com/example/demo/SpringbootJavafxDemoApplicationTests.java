package com.example.demo;

import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.User;
import com.example.demo.sample.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJavafxDemoApplication.class)
class SpringbootJavafxDemoApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    public void testFindById() {
        User user = userMapper.selectById(1);
        Date recentTime = user.getRecentTime();
        System.out.println("DateUtils.strDate(recentTime) = " + DateUtils.strDate(recentTime));
    }

    @Test
    public void testdoLogin() {

    }

}
