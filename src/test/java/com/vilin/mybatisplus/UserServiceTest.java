package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testGetCount() {
    // SELECT COUNT( * ) AS total FROM user
    long count = userService.count();
    System.out.println("total count = " + count);
  }

  @Test
  public void testBatchInsert() {
    // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    List<User> list = new ArrayList<>();
    List<User> userList = IntStream.rangeClosed(1, 10)
        .mapToObj(i -> new User(null, "Leo" + i, 20 + i, "Leo" + i + "@vilin.com")).collect(
            Collectors.toList());
    boolean result = userService.saveBatch(userList);
    System.out.println("save 10 users by batch : " + result);
  }


}
