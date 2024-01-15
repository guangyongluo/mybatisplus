package com.vilin.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vilin.mybatisplus.entity.User;
import com.vilin.mybatisplus.mapper.UserMapper;
import com.vilin.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
