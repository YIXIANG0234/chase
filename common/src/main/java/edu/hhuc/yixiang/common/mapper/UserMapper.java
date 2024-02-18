package edu.hhuc.yixiang.common.mapper;

import com.mybatisflex.core.BaseMapper;
import edu.hhuc.yixiang.common.entity.Role;
import edu.hhuc.yixiang.common.entity.SystemResource;
import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.entity.UserGroup;

import java.util.List;

/**
 * 用户表 映射层。
 *
 * @author yixiang
 * @since 2024-01-21
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserGroup> queryUserGroup(String userId);

    List<Role> queryRole(List<Long> groupIds);

    List<SystemResource> queryAuthority(List<Long> roleIds);
}
