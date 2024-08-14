package edu.hhuc.yixiang.service.core.log.function;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 自定义解析函数
 * @date 2023/12/29 11:09:14
 */
public interface IParseFunction<T> {
    /**
     * 方法名称
     *
     * @return SpEL表达式使用的自定义方法名称
     */
    String functionName();


    /**
     * 自定义方法执行逻辑
     * @param args 参数
     * @return 返回值
     */
    T apply(Object... args);
}
