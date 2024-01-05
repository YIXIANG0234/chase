package edu.hhuc.yixiang.repository.dao;

import edu.hhuc.yixiang.common.entity.DistributedSequence;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:59:07
 */
public interface DistributedSequenceDao {
    List<String> queryAllBusinessType();

    void updateMaxSequence(String businessType);

    DistributedSequence querySequence(String businessType);

    DistributedSequence updateAndGetSequence(String businessType);
}
