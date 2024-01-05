package edu.hhuc.yixiang.service.core;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:24:01
 */
public interface DistributedSequenceService {

    Long getSequence(String businessType);
}
