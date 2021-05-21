package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testCount(){
        int count = userService.count();
        System.out.println("总记录数:" + count);
    }

    @Test
    public void testSaveBatch(){
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("大大" + i);
            user.setAge(20+i);
            users.add(user);
        }

        boolean result = userService.saveBatch(users);
        System.out.println("是否插入成功:" + result);
    }

    @Test
    public void testListAllByName(){
        List<User> users = userService.listAllByName("Sandy");
        users.forEach(System.out::println);
    }
}
