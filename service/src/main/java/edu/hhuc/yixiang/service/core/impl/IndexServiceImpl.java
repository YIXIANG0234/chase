package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.annotation.DistributedLock;
import edu.hhuc.yixiang.service.core.IndexService;
import org.springframework.stereotype.Service;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/24 11:24:17
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    @DistributedLock(key = "test")
    public String index() {
        return "This is a index page;";
    }
}
