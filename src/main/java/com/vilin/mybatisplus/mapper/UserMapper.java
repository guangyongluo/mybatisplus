package com.vilin.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vilin.mybatisplus.entity.User;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

  /**
   * select user info convert to map by user id
   * @param id
   * @return
   */
  @MapKey("uid")
  Map<String, Object> selectMapById(Long id);

  /**
   * select users by page, and then divide result to pages.
   * @param page mybatis-plus support page plugin, must be the first parameter.
   * @param age
   * @return
   */
  Page<User> selectUserByAge(@Param("page") Page<User> page, @Param("age") Integer age);

}
