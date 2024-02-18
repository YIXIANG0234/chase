package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 用户组 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class UserGroupTableDef extends TableDef {

    /**
     * 用户组
     */
    public static final UserGroupTableDef USER_GROUP = new UserGroupTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 用户组描述
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
     * 用户组名称
     */
    public final QueryColumn GROUP_NAME = new QueryColumn(this, "group_name");

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
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_NAME, REMARK, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public UserGroupTableDef() {
        super("", "user_group");
    }

}
