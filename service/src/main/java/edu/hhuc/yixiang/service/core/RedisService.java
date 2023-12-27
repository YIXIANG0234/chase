package edu.hhuc.yixiang.service.core;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 16:20:02
 */
public interface RedisService {
    void distributedLock();
    void tryLock();
}
