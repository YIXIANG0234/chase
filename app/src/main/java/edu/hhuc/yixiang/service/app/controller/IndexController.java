package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.RedisRequest;
import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.mapper.UserMapper;
import edu.hhuc.yixiang.common.utils.JsonUtil;
import edu.hhuc.yixiang.service.core.IndexService;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/24 11:19:53
 */
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/index")
    @PreAuthorize(value = "hasAuthority('test')")
    public BaseResponse<String> index() {
        List<User> userList = userMapper.selectAll();
        String json = JsonUtil.toJson(userList);
        System.out.println(json);

        List<User> list = JsonUtil.parseList(json, User.class);
        System.out.println("list.size：" + list.size());
        return BaseResponse.ofSuccess(indexService.index());
    }

    @PostMapping("/redis")
    public BaseResponse<String> redis(@RequestBody RedisRequest request) {
        if (Objects.nonNull(request.getExpireSeconds())) {
            RedisHelper.set(request.getKey(), request.getValue(), request.getExpireSeconds());
        } else {
            RedisHelper.set(request.getKey(), request.getValue());
        }
        return BaseResponse.ofSuccess(RedisHelper.get(request.getKey()) + ":" + RedisHelper.ttl(request.getKey()));
    }
}
