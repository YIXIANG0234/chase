package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.base.BaseResponse;
import edu.hhuc.yixiang.common.dto.RedisRequest;
import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.mapper.UserMapper;
import edu.hhuc.yixiang.common.utils.JsonUtil;
import edu.hhuc.yixiang.service.helper.RedisHelper;
import edu.hhuc.yixiang.service.core.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
