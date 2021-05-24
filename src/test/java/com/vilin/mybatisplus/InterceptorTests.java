package com.vilin.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vilin.mybatisplus.entity.Product;
import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.ProductMapper;
import com.vilin.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testSelectPage() {
        Page<User> pageParam = new Page<>(2, 5);
        userMapper.selectPage(pageParam, null);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);

        long total = pageParam.getTotal();
        System.out.println("总页数:" + total);

        System.out.println("是否有下一页:" + pageParam.hasNext());
        System.out.println("是否有上一页:" + pageParam.hasPrevious());
    }

    @Test
    public void testSelectPageByAge() {

        Page<User> pageParam = new Page<>(1, 5);
        userMapper.selectPageByAge(pageParam, 20);

        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);

    }

    @Test
    public void testConcurrentUpdate() {

        //小李取数据
        Product product1 = productMapper.selectById(1L);

        //小王取数据
        Product product2 = productMapper.selectById(1L);

        //小李修改+50
        product1.setPrice(product1.getPrice() + 50);
        int result1 = productMapper.updateById(product1);
        System.out.println("小李修改结果：" + result1);

        //小王修改-30
        product2.setPrice(product2.getPrice() - 30);
        int result2 = productMapper.updateById(product2);
        System.out.println("小王修改结果：" + result2);

        if(result2 == 0){
            //失败重试
            product2 = productMapper.selectById(1L);
            product2.setPrice(product2.getPrice() - 30);
            result2 = productMapper.updateById(product2);
            System.out.println("小王重新修改结果：" + result2);
        }

        //老板看价格
        Product product3 = productMapper.selectById(1L);
        System.out.println("老板看到的价格：" + product3.getPrice());
    }
}
