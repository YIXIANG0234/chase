package edu.hhuc.yixiang.common.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

/**
 * 用户表 表定义层。
 *
 * @author yixiang
 * @since 2024-01-26
 */
public class UserTableDef extends TableDef {

    /**
     * 用户表
     */
    public static final UserTableDef USER = new UserTableDef();

    /**
     * id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 加密使用的盐
     */
    public final QueryColumn SALT = new QueryColumn(this, "salt");

    /**
     * 状态，activate：启用，forbidden：禁用
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 用户名
     */
    public final QueryColumn NICK_NAME = new QueryColumn(this, "nick_name");

    /**
     * 加密后的密码
     */
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

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
     * 登陆次数
     */
    public final QueryColumn LOGIN_COUNT = new QueryColumn(this, "login_count");

    /**
     * 上次登录时间
     */
    public final QueryColumn RECENT_LOGIN_TIME = new QueryColumn(this, "recent_login_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, NICK_NAME, PASSWORD, SALT, STATUS, RECENT_LOGIN_TIME, LOGIN_COUNT, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY};

    public UserTableDef() {
        super("", "user");
    }

}
