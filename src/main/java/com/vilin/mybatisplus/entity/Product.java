package com.vilin.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
  private Long id;
  private String name;
  private Integer price;
  @Version
  private Integer version;

}
