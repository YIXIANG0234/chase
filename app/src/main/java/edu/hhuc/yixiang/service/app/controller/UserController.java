package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 14:21:39
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/info")
    public BaseResponse userDetails() {
        return BaseResponse.ofSuccess();
    }
}
