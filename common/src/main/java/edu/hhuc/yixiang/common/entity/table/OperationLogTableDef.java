package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 *  表定义层。
 *
 * @author yixiang
 * @since 2023-12-30
 */
public class OperationLogTableDef extends TableDef {

    /**
     * 
     */
    public static final OperationLogTableDef OPERATION_LOG = new OperationLogTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 操作ip
     */
    public final QueryColumn IP = new QueryColumn(this, "ip");

    /**
     * 日志内容
     */
    public final QueryColumn CONTENT = new QueryColumn(this, "content");

    /**
     * 操作结束时间
     */
    public final QueryColumn END_TIME = new QueryColumn(this, "end_time");

    /**
     * 操作耗时
     */
    public final QueryColumn DURATION = new QueryColumn(this, "duration");

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
     * 操作开始时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * updated_at
     */
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    /**
     * updated_by
     */
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    /**
     * 业务数据id
     */
    public final QueryColumn BUSINESS_ID = new QueryColumn(this, "business_id");

    /**
     * 操作类型
     */
    public final QueryColumn OPERATOR_TYPE = new QueryColumn(this, "operator_type");

    /**
     * 操作人
     */
    public final QueryColumn OPERATOR_USER = new QueryColumn(this, "operator_user");

    /**
     * 操作模块
     */
    public final QueryColumn OPERATOR_MODULE = new QueryColumn(this, "operator_module");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, OPERATOR_USER, CONTENT, BUSINESS_ID, OPERATOR_TYPE, OPERATOR_MODULE, IP, START_TIME, END_TIME, DURATION, IS_DELETED, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public OperationLogTableDef() {
        super("", "operation_log");
    }

}
