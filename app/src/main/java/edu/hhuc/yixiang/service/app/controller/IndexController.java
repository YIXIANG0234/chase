package edu.hhuc.yixiang.service.app.controller;

import edu.hhuc.yixiang.common.entity.User;
import edu.hhuc.yixiang.common.mapper.UserMapper;
import edu.hhuc.yixiang.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/index")
    public String index() {
        List<User> userList = userMapper.selectAll();
        return indexService.index();
    }
}
