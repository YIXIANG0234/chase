package edu.hhuc.yixiang.repository.dao.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import edu.hhuc.yixiang.common.base.Sorter;
import edu.hhuc.yixiang.common.dto.OperationLogDTO;
import edu.hhuc.yixiang.common.entity.OperationLog;
import edu.hhuc.yixiang.common.mapper.OperationLogMapper;
import edu.hhuc.yixiang.repository.dao.OperationLogDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static edu.hhuc.yixiang.common.entity.table.OperationLogTableDef.OPERATION_LOG;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/29 21:37:05
 */
@Repository
public class OperationLogDaoImpl implements OperationLogDao {
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public void saveOrUpdate(OperationLogDTO record) {
        OperationLog log = new OperationLog();
        BeanUtils.copyProperties(record, log);
        operationLogMapper.insertOrUpdateSelective(log);
    }

    // GWH TODO: 2023/12/30 感觉需要改造下
    @Override
    public Page<OperationLog> page(OperationLogDTO record, Sorter sorter) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select(OPERATION_LOG.ALL_COLUMNS)
                .from(OPERATION_LOG)
                .where(OPERATION_LOG.IS_DELETED.eq(0))
                .and(OPERATION_LOG.OPERATOR_USER.eq(record.getOperatorUser()))
                .and(OPERATION_LOG.BUSINESS_ID.eq(record.getBusinessId()))
                .and(OPERATION_LOG.OPERATOR_TYPE.eq(record.getOperatorType()))
                .and(OPERATION_LOG.OPERATOR_MODULE.eq(record.getOperatorModule()))
                .and(OPERATION_LOG.CONTENT.likeRaw(record.getContent()))
                .orderBy(sorter.getSortKey(), sorter.getAsc());
        return operationLogMapper.paginate(1, 10, queryWrapper);
    }

}
