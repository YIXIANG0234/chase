package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.service.core.DistributedSequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/5 11:28:49
 */
@Slf4j
@RestController
@RequestMapping("/sequence")
public class DistributedSequenceController {

    @Autowired
    private DistributedSequenceService distributedSequenceService;

    @GetMapping("/get/{businessType}")
    public BaseResponse<Long> getSequence(@PathVariable("businessType") String businessType) {
        long sequence = distributedSequenceService.getSequence(businessType);
        log.info("获取到id：{}", sequence);
        return BaseResponse.ofSuccess(sequence);
    }
}
