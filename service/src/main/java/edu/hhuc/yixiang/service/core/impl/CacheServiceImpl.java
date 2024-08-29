package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.config.CacheManagerConfig;
import edu.hhuc.yixiang.common.dto.AnyInputDTO;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.dto.UserDetailDTO;
import edu.hhuc.yixiang.common.utils.JsonUtil;
import edu.hhuc.yixiang.repository.dao.OperationLogDao;
import edu.hhuc.yixiang.service.core.CacheService;
import edu.hhuc.yixiang.service.core.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/8/29 14:44:09
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    @Autowired
    private OperationLogDao operationLogDao;

    @Autowired
    private UserService userService;

    @Override
    @Cacheable(value = "verifyCode", key = "'test'", cacheManager = CacheManagerConfig.REDIS_CACHE_MANAGER)
    public String randomCode() {
        String code = RandomStringUtils.randomAlphabetic(4);
        log.info("verify code：{}", code);
        return code;
    }

    @Override
    @Cacheable(value = "operation")
    public OperationLogDTO queryOperation(long id) {
        OperationLogDTO logDTO = OperationLogDTO.convertFrom(operationLogDao.find(id));
        log.info("query operation：{}", Objects.isNull(logDTO) ? "" : logDTO.getContent());
        return logDTO;
    }

    @Override
    @Cacheable(value = "user", cacheManager = CacheManagerConfig.CAFFEINE_CACHE_MANAGER)
    public UserDetailDTO queryUser(AnyInputDTO inputDTO) {
        UserDetailDTO userDetailDTO = userService.queryUserDetail(inputDTO.getValue());
        log.info("query user：{}", JsonUtil.toJson(userDetailDTO));
        return userDetailDTO;
    }
}
