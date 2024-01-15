package com.vilin.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserWrapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testSelectList() {
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("user_name", "o").between("age", 20, 30).isNotNull("email");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  @Test
  public void testSelectListBySort() {
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY user_name ASC,age DESC
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByAsc("user_name").orderByDesc("age");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  @Test
  public void testDelete() {
    // UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email LIKE ?)
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("email", "baomidou");
    int result = userMapper.delete(queryWrapper);
    System.out.println("result = " + result);
  }

  @Test
  public void testUpdate() {
    // UPDATE t_user SET age=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.gt("age", 20).like("user_name", "Leo3").or().isNull("email");
    User user = new User();
    user.setAge(38);
    user.setEmail("test@vilin.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println("result = " + result);
  }

  @Test
  public void testUpdateByPriority() {
    // UPDATE t_user SET age=?, email=? WHERE is_deleted=0 AND (age > ? AND (user_name LIKE ? OR email IS NULL))
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.gt("age", 20)
        .and(wrapper -> wrapper.like("user_name", "Leo8").or().isNull("email"));
    User user = new User();
    user.setAge(38);
    user.setEmail("test@vilin.com");
    int result = userMapper.update(user, queryWrapper);
    System.out.println("result = " + result);
  }

  @Test
  public void testSelectMaps() {
    // SELECT user_name,age,email FROM t_user WHERE is_deleted=0
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("user_name", "age", "email");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
    maps.forEach(System.out::println);
  }

  @Test
  public void testSubSQL() {
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where is_deleted =0 and uid < 100))
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.inSql("uid", "select uid from t_user where is_deleted =0 and uid < 100");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
  }

  @Test
  public void testUpdateWrapper() {
    // UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (age > ? AND (user_name LIKE ? OR email IS NULL))
    UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
    queryWrapper.gt("age", 20)
        .and(wrapper -> wrapper.like("user_name", "Leo9").or().isNull("email"))
        .set("user_name", "XiaoHei").set("email", "XiaoHei@vilin.com");
    int result = userMapper.update(queryWrapper);
    System.out.println("result : " + result);
  }

  @Test
  public void testLambdaQueryWrapper(){
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.gt(User::getAge, 20).like(User::getName, "Leo3").or().isNull(User::getEmail);
    List<User> users = userMapper.selectList(lambdaQueryWrapper);
    users.forEach(System.out::println);
  }

  @Test
  public void testLambdaUpdateWrapper(){
    // UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (age > ? AND (user_name LIKE ? OR email IS NULL))
    LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
    lambdaUpdateWrapper.gt(User::getAge, 20)
        .and(wrapper -> wrapper.like(User::getName, "Leo9").or().isNull(User::getEmail))
        .set(User::getName, "XiaoHei").set(User::getEmail, "XiaoHei@vilin.com");
    int result = userMapper.update(lambdaUpdateWrapper);
    System.out.println("result : " + result);
  }
}
