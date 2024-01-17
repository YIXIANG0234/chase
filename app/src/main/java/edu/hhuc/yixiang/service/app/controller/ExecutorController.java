package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.ServiceCallDTO;
import edu.hhuc.yixiang.service.core.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/10 11:16:21
 */

@RequestMapping("/execute")
@RestController
public class ExecutorController {

    @Autowired
    private ProcessorService processorService;

    @PostMapping("/method")
    public BaseResponse<Object> execute(@RequestBody ServiceCallDTO serviceCallDTO) {
        return BaseResponse.ofSuccess(processorService.call(serviceCallDTO));
    }

}
