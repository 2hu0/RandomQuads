package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.sample.mapper.RecordMapper;
import com.example.demo.sample.mapper.UserMapper;
import com.example.demo.sample.model.Record;
import com.example.demo.sample.model.User;
import com.example.demo.sample.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJavafxDemoApplication.class)
class SpringbootJavafxDemoApplicationTests {

    @Autowired
    public UserMapper userMapper;
    @Autowired
    public RecordMapper recordMapper;

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
    public void testSelect() {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        Record record = new Record();
        record.setRecordName("lwz");
        queryWrapper.select("record_user_name","record_user_point")
                .eq("record_user_name",record.getRecordName())
                .orderByDesc("record_time");
        List<Object> objects = recordMapper.selectObjs(queryWrapper);

        List<Record> records = recordMapper.selectList(queryWrapper);
        List<Map<String, Object>> maps = recordMapper.selectMaps(queryWrapper);
    }

}
