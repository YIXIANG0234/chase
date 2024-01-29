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
 * 权限表 实体类。
 *
 * @author yixiang
 * @since 2024-01-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "system_resource")
public class SystemResource implements Serializable {

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * 访问所需的权限点
     */
    private String permissionCode;

    /**
     * 所属上级菜单id
     */
    private Long parentId;

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
