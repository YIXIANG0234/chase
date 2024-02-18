package edu.hhuc.yixiang.repository.dao;

import edu.hhuc.yixiang.common.entity.Role;
import edu.hhuc.yixiang.common.entity.SystemResource;
import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.entity.UserGroup;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 用户和权限相关的都放在这里
 * @date 2024/1/21 21:05:05
 */
public interface UserDao {

    /**
     * @param nickName 根据用户名查询
     * @return
     */
    User queryUserByNickName(String nickName);

    User queryUserByUserId(String userId);

    List<UserGroup> queryUserGroup(String userId);

    List<Role> queryRole(List<Long> groupIds);

    List<SystemResource> queryAuthority(List<Long> roleIds);

}
