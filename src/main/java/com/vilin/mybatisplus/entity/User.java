package com.vilin.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vilin.mybatisplus.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@TableName("t_user")
public class User {

    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;

    @TableLogic
    private Boolean isDeleted;

    public User(Long id, String name, Integer age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
