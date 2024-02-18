package edu.hhuc.yixiang.repository.dao.impl;

import com.mybatisflex.core.query.QueryWrapper;
import edu.hhuc.yixiang.common.entity.Role;
import edu.hhuc.yixiang.common.entity.SystemResource;
import edu.hhuc.yixiang.common.entity.User;
import static edu.hhuc.yixiang.common.entity.table.UserTableDef.USER;

import edu.hhuc.yixiang.common.entity.UserGroup;
import edu.hhuc.yixiang.common.mapper.UserMapper;
import edu.hhuc.yixiang.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/21 21:05:32
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserByNickName(String nickName) {
        return userMapper.selectOneByQuery(QueryWrapper.create().where(USER.NICK_NAME.eq(nickName)));
    }

    @Override
    public User queryUserByUserId(String userId) {
        return userMapper.selectOneByQuery(QueryWrapper.create().where(USER.USER_ID.eq(userId)));
    }

    @Override
    public List<UserGroup> queryUserGroup(String userId) {
        return userMapper.queryUserGroup(userId);
    }

    @Override
    public List<Role> queryRole(List<Long> groupIds) {
        return userMapper.queryRole(groupIds);
    }

    @Override
    public List<SystemResource> queryAuthority(List<Long> roleIds) {
        return userMapper.queryAuthority(roleIds);
    }
}
