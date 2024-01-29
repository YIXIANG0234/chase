package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.service.component.sequence.DistributedSequenceGenerator;
import edu.hhuc.yixiang.service.core.DistributedSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/5 11:32:28
 */
@Service
public class DistributedSequenceServiceImpl implements DistributedSequenceService {
    @Autowired
    private DistributedSequenceGenerator distributedSequenceGenerator;

    @Override
    public Long getSequence(String businessType) {
        return distributedSequenceGenerator.generateSequence(businessType);
    }
}
