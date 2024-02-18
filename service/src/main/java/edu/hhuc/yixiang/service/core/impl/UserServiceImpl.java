package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.dto.UserDetailDTO;
import edu.hhuc.yixiang.common.dto.UserGroupDTO;
import edu.hhuc.yixiang.common.dto.UserRoleDTO;
import edu.hhuc.yixiang.common.entity.Role;
import edu.hhuc.yixiang.common.entity.SystemResource;
import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.entity.UserGroup;
import edu.hhuc.yixiang.common.utils.LambdaUtils;
import edu.hhuc.yixiang.repository.dao.UserDao;
import edu.hhuc.yixiang.service.core.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 15:50:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetailDTO queryUserDetail(String userIdOrNickName) {
        User user = userDao.queryUserByUserId(userIdOrNickName);
        if (Objects.isNull(user)) {
            user = userDao.queryUserByNickName(userIdOrNickName);
        }
        if (Objects.isNull(user)) {
            return null;
        }
        List<UserGroup> groups = userDao.queryUserGroup(user.getUserId());

        List<UserGroupDTO> userGroups = new ArrayList<>();
        for (UserGroup group : groups) {
            UserGroupDTO userGroupDTO = new UserGroupDTO();
            userGroupDTO.setId(group.getId());
            userGroupDTO.setGroupName(group.getGroupName());
            userGroupDTO.setRoles(new ArrayList<>());
            List<Role> roles = userDao.queryRole(LambdaUtils.map(groups, UserGroup::getId));
            if (CollectionUtils.isNotEmpty(roles)) {
                for (Role role : roles) {
                    UserRoleDTO userRoleDTO = new UserRoleDTO();
                    userRoleDTO.setId(role.getId());
                    userRoleDTO.setParentId(role.getParentId());
                    userRoleDTO.setRoleName(role.getRoleName());
                    userRoleDTO.setRoleCode(role.getRoleCode());
                    List<SystemResource> resources = userDao.queryAuthority(LambdaUtils.map(roles, Role::getId));
                    userRoleDTO.setAuthorities(LambdaUtils.map(resources, SystemResource::getPermissionCode));
                    userGroupDTO.getRoles().add(userRoleDTO);
                }
            }
            userGroups.add(userGroupDTO);
        }
        return new UserDetailDTO(user, userGroups);
    }
}
