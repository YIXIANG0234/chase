package edu.hhuc.yixiang.service.component.sequence;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:25:42
 */
public interface DistributedSequenceGenerator {
    Long generateSequence(String businessType);
}
