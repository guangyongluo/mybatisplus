package com.vilin.mybatisplus;

import com.vilin.mybatisplus.entity.Product;
import com.vilin.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductMapperTest {

  @Autowired
  private ProductMapper productMapper;

  @Test
  public void testProduct() {
    Product productLi = productMapper.selectById(1);
    System.out.println("xiao li search product price : " + productLi.getPrice());

    Product productWang = productMapper.selectById(1);
    System.out.println("xiao wang search product price : " + productWang.getPrice());

    productLi.setPrice(productLi.getPrice() + 50);
    productMapper.updateById(productLi);

    productWang.setPrice(productWang.getPrice() - 30);
    int result = productMapper.updateById(productWang);

    if(result == 0) {
      productWang = productMapper.selectById(1);
      productWang.setPrice(productWang.getPrice() - 30);
      productMapper.updateById(productWang);
    }

    Product productBoss = productMapper.selectById(1);
    System.out.println("boss search the product price is " + productBoss.getPrice());
  }
}
