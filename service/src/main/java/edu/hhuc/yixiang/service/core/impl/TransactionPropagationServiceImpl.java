package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.enums.UserStatusEnum;
import edu.hhuc.yixiang.repository.dao.UserDao;
import edu.hhuc.yixiang.service.core.TransactionPropagationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/10/29 16:26:04
 */
@Slf4j
@Service
public class TransactionPropagationServiceImpl implements TransactionPropagationService {
    @Autowired
    private TransactionPropagationService transactionPropagationService;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void methodA() throws Exception {
        User user = createUser("2");
        userDao.addUser(user);
        transactionPropagationService.methodB();
        throw new SQLException("新增用户异常");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void methodB() throws Exception {
        User user = createUser("3");
        userDao.addUser(user);
    }

    private User createUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setNickName("user" + userId);
        user.setPassword("password" + userId);
        user.setSalt("salt" + userId);
        user.setStatus(UserStatusEnum.ACTIVATE.getCode());
        return user;
    }
}
