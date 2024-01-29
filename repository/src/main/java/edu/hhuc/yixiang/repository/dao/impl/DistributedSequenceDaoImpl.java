package edu.hhuc.yixiang.repository.dao.impl;

import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import edu.hhuc.yixiang.common.entity.DistributedSequence;
import edu.hhuc.yixiang.common.mapper.DistributedSequenceMapper;
import edu.hhuc.yixiang.repository.dao.DistributedSequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.hhuc.yixiang.common.entity.table.DistributedSequenceTableDef.DISTRIBUTED_SEQUENCE;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:59:38
 */
@Repository
public class DistributedSequenceDaoImpl implements DistributedSequenceDao {

    @Autowired
    private DistributedSequenceMapper distributedSequenceMapper;

    @Override
    public List<String> queryAllBusinessType() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(DISTRIBUTED_SEQUENCE.BUSINESS_TYPE)
                .from(DISTRIBUTED_SEQUENCE)
                .where(DISTRIBUTED_SEQUENCE.IS_DELETED.eq(0))
                .groupBy(DISTRIBUTED_SEQUENCE.BUSINESS_TYPE);
        return distributedSequenceMapper.selectListByQueryAs(queryWrapper, String.class);
    }

    @Override
    public void updateMaxSequence(String businessType) {
        DistributedSequence record = UpdateEntity.of(DistributedSequence.class);
        UpdateWrapper<DistributedSequence> wrapper = UpdateWrapper.of(record);
        wrapper.set(DISTRIBUTED_SEQUENCE.MAX_ID, DISTRIBUTED_SEQUENCE.MAX_ID.add(DISTRIBUTED_SEQUENCE.STEP));
        QueryWrapper queryWrapper = QueryWrapper.create().where(DISTRIBUTED_SEQUENCE.BUSINESS_TYPE.eq(businessType));
        distributedSequenceMapper.updateByQuery(record, queryWrapper);
    }

    @Override
    public DistributedSequence querySequence(String businessType) {
        return distributedSequenceMapper.selectOneByCondition(QueryCondition.create(DISTRIBUTED_SEQUENCE.BUSINESS_TYPE, businessType));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DistributedSequence updateAndGetSequence(String businessType) {
        updateMaxSequence(businessType);
        return querySequence(businessType);
    }
}
