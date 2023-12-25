package edu.hhuc.yixiang.service.index;

import org.springframework.stereotype.Service;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/24 11:24:17
 */
@Service
public class IndexServiceImpl implements IndexService{
    @Override
    public String index() {
        return "This is a index page;";
    }
}
