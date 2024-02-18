package edu.hhuc.yixiang.common.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-权限关联表 实体类。
 *
 * @author yixiang
 * @since 2024-01-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "role_permission")
public class RolePermission implements Serializable {

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色拥有的权限code
     */
    private String permissionCode;

    /**
     * is_deleted
     */
    @Column(isLogicDelete = true)
    private Boolean isDeleted;

    /**
     * created_at
     */
    private Date createdAt;

    /**
     * created_by
     */
    private String createdBy;

    /**
     * updated_at
     */
    private Date updatedAt;

    /**
     * updated_by
     */
    private String updatedBy;

}
