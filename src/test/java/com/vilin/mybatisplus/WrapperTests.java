package com.vilin.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTests {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询名字中包含n，年龄大于10且小于20，email不为空的用户
     */
    @Test
    public void test1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //column:对应数据库表中的列名
        queryWrapper.like("username", "1")
                .between("age", 20, 30)
                .isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 按年龄降序查询用户，如果年龄相同则按id升序排序
     */
    @Test
    public void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //组装排序条件
        queryWrapper.orderByDesc("age").orderByAsc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 删除email为空的用户
     */
    @Test
    public void test3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("删除的记录数：" + result);
    }

    /**
     * 查询名字中包含n，且(年龄小于18或email为空的用户),并将这些用户的年龄设置为18，邮箱设置为user@qq.com
     */
    @Test
    public void test4() {

        //组装查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "n")
                .and(i -> i.lt("age", 18)
                        .or().isNull("email"));
        //组装更新条件
        User user = new User();
        user.setAge(18);
        user.setEmail("user@qq.com");

        //执行更新
        int result = userMapper.update(user, queryWrapper);
        System.out.println("更新了几条记录：" + result);
    }

    /**
     * 查询所有用户的用户姓名和年龄
     */
    @Test
    public void test5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //组装select语句
        queryWrapper.select("username", "age");

        //select语句通常会和selectMaps一起使用
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 使用子查询
     * 查询id不大于3的所有用户的id列表
     */
    @Test
    public void test6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 3");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询名字中包含n，且(年龄小于18或email为空的用户),并将这些用户的年龄设置为18，邮箱设置为user@qq.com
     */
    @Test
    public void test7() {

        //组装更新条件、查询条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 18)
                .set("email", "user@qq.com")
                .like("username", "c")
                .and(i -> i.lt("age", 18)
                        .or().isNull("email"));

        User user = new User();
        //执行更新
        int result = userMapper.update(user, updateWrapper);
        System.out.println("更新了几条记录：" + result);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test8() {
        String username = "n";
        Integer ageBegin = null;
        Integer ageEnd = 20;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(username), "username", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test9() {
        String username = "n";
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询名字中包含n，且(年龄小于18或email为空的用户),并将这些用户的年龄设置为18，邮箱设置为user@qq.com
     */
    @Test
    public void test10() {

        //组装更新条件、查询条件
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getAge, 18)
                .set(User::getEmail, "user@qq.com")
                .like(User::getName, "c")
                .and(i -> i.lt(User::getAge, 18)
                        .or().isNull(User::getName));

        User user = new User();
        //执行更新
        int result = userMapper.update(user, updateWrapper);
        System.out.println("更新了几条记录：" + result);
    }
}
