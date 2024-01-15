package com.vilin.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusPluginsTest {

  private final static int PAGE_SIZE = 10;

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testPage(){
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?
    Page<User> page = new Page<>(1, PAGE_SIZE);
    Page<User> userPage = userMapper.selectPage(page, null);
    System.out.println(userPage);

    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?,?
    Page<User> page2 = new Page<>(2, PAGE_SIZE);
    Page<User> userPage2 = userMapper.selectPage(page2, null);
    System.out.println(userPage2);
    System.out.println("total pages : " + userPage2.getPages());
    System.out.println("total records : " + userPage2.getTotal());
    System.out.println("current page : " + userPage2.getCurrent());
    System.out.println("current page records : " + userPage2.getRecords());
    System.out.println("page size : " + userPage2.getSize());
    System.out.println("have next page : " + userPage2.hasNext());
    System.out.println("have previous page : " + userPage2.hasPrevious());
  }

  @Test
  public void testSelectUserByAge(){
    Page<User> page = new Page<>(1, 10);
    Page<User> userPage = userMapper.selectUserByAge(page, 20);
    System.out.println(userPage);
    System.out.println("total pages : " + userPage.getPages());
    System.out.println("total records : " + userPage.getTotal());
    System.out.println("current page : " + userPage.getCurrent());
    System.out.println("current page records : " + userPage.getRecords());
    System.out.println("page size : " + userPage.getSize());
    System.out.println("have next page : " + userPage.hasNext());
    System.out.println("have previous page : " + userPage.hasPrevious());
  }
}
