package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testInsertUser() {
    // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    User user = new User();
    user.setName("张三");
    user.setAge(23);
    user.setEmail("ZhangSan@vilin.com");
    int result = userMapper.insert(user);
    System.out.println("result : " + result);
    System.out.println("id : " + user.getId());
  }

  @Test
  public void testDeleteById() {
    // DELETE FROM user WHERE id=?
    int result = userMapper.deleteById(25L);
    System.out.println("result : " + result);
  }

  public void testLogicDeleteById(){
    // UPDATE t_user SET is_deleted=1 WHERE uid=? AND is_deleted=0
    int result = userMapper.deleteById(25L);
    System.out.println("result : " + result);
  }

  @Test
  public void testDeleteByMap() {
    // DELETE FROM user WHERE (name = ? AND age = ?)
    Map<String, Object> map = new HashMap<>();
    map.put("name", "张三");
    map.put("age", 23);
    int result = userMapper.deleteByMap(map);
    System.out.println("result : " + result);
  }

  @Test
  public void testDeleteBatchIds() {
    // DELETE FROM user WHERE id IN ( ? , ? , ? , ? , ? )
    List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L);
    int result = userMapper.deleteBatchIds(list);
    System.out.println("result : " + result);
  }

  @Test
  public void testUpdateById() {
    // UPDATE user SET name=?, email=? WHERE id=?
    User user = new User();
    user.setId(1L);
    user.setName("Leo");
    user.setEmail("Leo@vilin.com");
    int result = userMapper.updateById(user);
    System.out.println("result : " + result);
  }

  @Test
  public void testSelectById() {
    // SELECT id,name,age,email FROM user WHERE id=?
    User user = userMapper.selectById(1L);
    System.out.println(user);
  }

  @Test
  public void testSelectBatchIds() {
    // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
    List<Long> list = Arrays.asList(1L, 2L, 3L);
    List<User> users = userMapper.selectBatchIds(list);
    users.forEach(System.out::println);
  }

  @Test
  public void testSelectByMap(){
    // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
    Map<String, Object> map = new HashMap<>();
    map.put("name", "Leo");
    map.put("age", 18);
    List<User> users = userMapper.selectByMap(map);
    users.forEach(System.out::println);
  }

  @Test
  public void testSelectList() {
    // SELECT id,name,age,email FROM user
    List<User> users = userMapper.selectList(null);
    users.forEach(System.out::println);
  }

  @Test
  public void testSelectMapById() {
    // SELECT id, name, age, email FROM USER WHERE id = ?
    Map<String, Object> map = userMapper.selectMapById(1L);
    System.out.println("map size = " + map.size());
    System.out.println(map);
  }
}
