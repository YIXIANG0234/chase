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
 *  实体类。
 *
 * @author yixiang
 * @since 2024-01-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "operation_log")
public class OperationLog implements Serializable {

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 操作人
     */
    private String operatorUser;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 业务数据id
     */
    private String businessId;

    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 操作模块
     */
    private String operatorModule;

    /**
     * 操作ip
     */
    private String ip;

    /**
     * 操作开始时间
     */
    private Date startTime;

    /**
     * 操作结束时间
     */
    private Date endTime;

    /**
     * 操作耗时
     */
    private Integer duration;

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
