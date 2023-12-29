package edu.hhuc.yixiang.repository.dao;

import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.entity.OperationLog;
import edu.hhuc.yixiang.common.mapper.OperationLogMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 21:37:05
 */
@Repository
public class OperationLogDao {
    @Autowired
    private OperationLogMapper operationLogMapper;

    public void saveOrUpdate(OperationLogDTO record) {
        OperationLog log = new OperationLog();
        BeanUtils.copyProperties(record, log);
        operationLogMapper.insertOrUpdateSelective(log);
    }
}
