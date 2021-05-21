package com.vilin.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vilin.mybatisplus.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByName(String name);
}
