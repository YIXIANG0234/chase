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
 * 用户组 实体类。
 *
 * @author yixiang
 * @since 2024-01-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_group")
public class UserGroup implements Serializable {

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组描述
     */
    private String remark;

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
