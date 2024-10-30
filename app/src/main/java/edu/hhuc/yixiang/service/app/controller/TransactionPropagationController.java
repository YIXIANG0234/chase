package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.service.core.TransactionPropagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/10/29 20:56:55
 */
@RestController
@RequestMapping("/transaction")
public class TransactionPropagationController {

    @Autowired
    private TransactionPropagationService transactionPropagationService;

    @GetMapping("/test")
    public BaseResponse<Void> test() throws Exception {
        transactionPropagationService.methodA();
        return BaseResponse.ofSuccess();
    }
}
