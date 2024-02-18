package edu.hhuc.yixiang.common.base;

import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/8 20:08:59
 */
public class UserHolder {
    private static final ThreadLocal<User> HOLDER = new ThreadLocal<>();

    public static User getUser() {
        User user = HOLDER.get();
        // GWH TODO: 2024/1/8 后续实现登陆系统进行优化
        if (Objects.isNull(user)) {
            user = new User("system", "system");
            HOLDER.set(user);
        }
        return user;
    }

    public static void setUser(User user) {
        HOLDER.set(user);
    }

    public static String getUserId() {
        return getUser().getUserId();
    }

    public static String getUserName() {
        return getUser().getUserName();
    }
}
