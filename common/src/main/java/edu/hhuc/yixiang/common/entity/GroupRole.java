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
 * 用户组-角色关联表 实体类。
 *
 * @author yixiang
 * @since 2024-01-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "group_role")
public class GroupRole implements Serializable {

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
     * 用户组id
     */
    private Long groupId;

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
