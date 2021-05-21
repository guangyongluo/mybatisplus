package com.vilin.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName(value = "t_user")
public class User {
//    @TableId(value = "uid", type = IdType.AUTO)
    @TableId(value = "uid")
    private Long id;
    @TableField(value = "username")
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted; // 0 false 未删除； 1 true 已删除
}
