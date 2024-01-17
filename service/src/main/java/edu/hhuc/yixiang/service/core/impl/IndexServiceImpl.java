package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.annotation.DistributedLock;
import edu.hhuc.yixiang.common.utils.DateUtil;
import edu.hhuc.yixiang.service.core.IndexService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
     * 用来测试ProcessorService#call(ServiceCallDTO)方法
     * @param date 时间
     */
    @Override
    public void print(Date date) {
        System.out.println(DateUtil.formatYmdHms(date));;
    }
}
