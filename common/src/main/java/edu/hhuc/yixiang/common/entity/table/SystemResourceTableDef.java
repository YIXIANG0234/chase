package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 权限表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class SystemResourceTableDef extends TableDef {

    /**
     * 权限表
     */
    public static final SystemResourceTableDef SYSTEM_RESOURCE = new SystemResourceTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 资源名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 资源类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 所属上级菜单id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

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
     * 访问所需的权限点
     */
    public final QueryColumn PERMISSION_CODE = new QueryColumn(this, "permission_code");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, TYPE, PERMISSION_CODE, PARENT_ID, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public SystemResourceTableDef() {
        super("", "system_resource");
    }

}
