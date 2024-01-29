package edu.hhuc.yixiang.common.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分布式id生成表 实体类。
 *
 * @author yixiang
 * @since 2024-01-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "distributed_sequence")
public class DistributedSequence implements Serializable {

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private BigInteger id;

    /**
     * id的业务分类
     */
    private String businessType;

    /**
     * 当前业务可获取的最大id
     */
    private Long maxId;

    /**
     * 每次获取的步长
     */
    private Integer step;

    /**
     * 业务类型描述
     */
    private String remark;

    /**
     * is_deleted
     */
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
