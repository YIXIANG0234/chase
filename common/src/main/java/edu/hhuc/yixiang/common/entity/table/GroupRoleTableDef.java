package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 用户组-角色关联表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class GroupRoleTableDef extends TableDef {

    /**
     * 用户组-角色关联表
     */
    public static final GroupRoleTableDef GROUP_ROLE = new GroupRoleTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 角色id
     */
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    /**
     * 用户组id
     */
    public final QueryColumn GROUP_ID = new QueryColumn(this, "group_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE_ID, GROUP_ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public GroupRoleTableDef() {
        super("", "group_role");
    }

}
