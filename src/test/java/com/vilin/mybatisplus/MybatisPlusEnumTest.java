package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.enums.SexEnum;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusEnumTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testInsert(){
    User user = new User();
    user.setName("admin");
    user.setAge(33);
    user.setSex(SexEnum.FEMALE);
    int result = userMapper.insert(user);
    System.out.println("result = " + result);
  }

  @Test
  public void testSelect(){
    User user = userMapper.selectById(16);
    System.out.println(user);
  }

}
