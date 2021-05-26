package com.vilin.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vilin.mybatisplus.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByName(String name);

    IPage<User> selectPageByAge(Page<?> page, Integer age);

}
