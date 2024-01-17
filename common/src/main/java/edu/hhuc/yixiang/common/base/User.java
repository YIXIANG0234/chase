package edu.hhuc.yixiang.common.base;

import lombok.Getter;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 20:07:57
 */
@Getter
public class User {
    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    private final String userId;
    private final String userName;
}
