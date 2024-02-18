package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 角色表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class RoleTableDef extends TableDef {

    /**
     * 角色表
     */
    public static final RoleTableDef ROLE = new RoleTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 角色描述
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 父级角色
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    /**
     * 角色code
     */
    public final QueryColumn ROLE_CODE = new QueryColumn(this, "role_code");

    /**
     * 角色名称
     */
    public final QueryColumn ROLE_NAME = new QueryColumn(this, "role_name");

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
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE_CODE, PARENT_ID, ROLE_NAME, REMARK, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public RoleTableDef() {
        super("", "role");
    }

}
