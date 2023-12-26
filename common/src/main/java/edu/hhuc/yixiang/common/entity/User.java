package edu.hhuc.yixiang.common.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表 实体类。
 *
 * @author yixiang
 * @since 2023-12-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user")
public class User implements Serializable {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * guid
     */
    private Long guid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 加密使用的盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态，activate：启用，forbidden：禁用
     */
    private String status;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 登陆次数
     */
    private Integer loginCount;

    /**
     * is_deleted
     */
    private Boolean isDeleted;

    /**
     * created_at
     */
    private LocalDateTime createdAt;

    /**
     * created_by
     */
    private String createdBy;

    /**
     * updated_at
     */
    private LocalDateTime updatedAt;

    /**
     * updated_by
     */
    private String updatedBy;

}
