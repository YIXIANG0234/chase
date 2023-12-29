package edu.hhuc.yixiang.common.annotation;

import edu.hhuc.yixiang.common.enums.OperatorModuleEnum;
import edu.hhuc.yixiang.common.enums.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/28 16:22:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
@Inherited
public @interface LogRecord {

    // 日志内容，支持EL表达式
    String content();

    // 操作人，支持EL表达式
    String operatorUser() default "admin";

    // 业务数据id，支持EL表达式
    String businessId() default "";

    OperatorTypeEnum operatorType();

    OperatorModuleEnum operatorModule();
}
