package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 角色-权限关联表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class RolePermissionTableDef extends TableDef {

    /**
     * 角色-权限关联表
     */
    public static final RolePermissionTableDef ROLE_PERMISSION = new RolePermissionTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 角色id
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

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
     * 角色拥有的权限code
     */
    public final QueryColumn PERMISSION_CODE = new QueryColumn(this, "permission_code");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE_ID, PERMISSION_CODE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public RolePermissionTableDef() {
        super("", "role_permission");
    }

}
