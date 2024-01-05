package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 分布式id生成表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-03
 */
public class DistributedSequenceTableDef extends TableDef {

    /**
     * 分布式id生成表
     */
    public static final DistributedSequenceTableDef DISTRIBUTED_SEQUENCE = new DistributedSequenceTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 每次获取的步长
     */
    public final QueryColumn STEP = new QueryColumn(this, "step");

    /**
     * 当前业务可获取的最大id
     */
    public final QueryColumn MAX_ID = new QueryColumn(this, "max_id");

    /**
     * 业务类型描述
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * created_at
     */
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    /**
     * created_by
     */
    public final QueryColumn CREATED_BY = new QueryColumn(this, "created_by");

    /**
     * is_deleted
     */
    public final QueryColumn IS_DELETED = new QueryColumn(this, "is_deleted");

    /**
     * updated_at
     */
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    /**
     * updated_by
     */
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    /**
     * id的业务分类
     */
    public final QueryColumn BUSINESS_TYPE = new QueryColumn(this, "business_type");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, BUSINESS_TYPE, MAX_ID, STEP, REMARK, IS_DELETED, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public DistributedSequenceTableDef() {
        super("", "distributed_sequence");
    }

}
