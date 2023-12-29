package edu.hhuc.yixiang.common.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *  实体类。
 *
 * @author yixiang
 * @since 2023-12-29
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
    private BigInteger id;

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
