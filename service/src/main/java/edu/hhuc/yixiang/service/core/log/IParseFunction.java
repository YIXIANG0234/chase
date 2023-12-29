package edu.hhuc.yixiang.service.core.log;

import org.apache.commons.lang3.StringUtils;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 自定义解析函数
 * @date 2023/12/29 11:09:14
 */
public interface IParseFunction {
    /**
     * 自定义函数后加该后缀，表示方法需要在目标方法执行前执行
     */
    String EXECUTE_BEFORE_PREFIX = ".before";

    static boolean executeBefore(String functionName) {
        return StringUtils.isNotBlank(functionName) && functionName.endsWith(EXECUTE_BEFORE_PREFIX);
    }

    String functionName();

    // GWH TODO: 2023/12/29 如何支持多个范型参数
     String apply(Object... args);
}
