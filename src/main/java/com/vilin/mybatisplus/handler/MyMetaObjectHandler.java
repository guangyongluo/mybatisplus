package com.vilin.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("插入时自动填充..............");

        //实现填充业务逻辑
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        //判断当前对象的自动填充属性是否已经进行了赋值
        Object age = this.getFieldValByName("age", metaObject);
        if(age == null) {
            log.info("age自动填充");
            this.strictInsertFill(metaObject, "age", Integer.class, 3);
        }

        //判断当前的对象的自动填充属性是否包含当前属性
        if(metaObject.hasSetter("author")){
            this.strictInsertFill(metaObject, "author", String.class, "无");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("修改时自动填充..............");

        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
