package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("后羿");
        user.setAge(28);
        user.setEmail("zhinv@qq.com");
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());

        int result = userMapper.insert(user);
        System.out.println("结构：" + result);
    }

    @Test
    public void testSelect() {
        User user = userMapper.selectById(1);
        System.out.println(user);

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5, 6));
        users.forEach(System.out::println);


        Map<String, Object> map = new HashMap<>();
        map.put("name", "建国");
        map.put("age", 74);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1395283724207214606L);
        user.setAge(36);
//        user.setUpdateTime(LocalDateTime.now());
        int result = userMapper.updateById(user);
        System.out.println("结果：" + result);
    }

    @Test
    public void testDelete(){
        int result = userMapper.deleteById(2L);
        System.out.println("结果:" + result);
    }

    @Test
    public void testSelectAllByName(){
        List<User> users = userMapper.selectAllByName("Jack");
        users.forEach(System.out::println);
    }


}
