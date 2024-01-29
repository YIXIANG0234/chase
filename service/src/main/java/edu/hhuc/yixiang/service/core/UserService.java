package edu.hhuc.yixiang.service.core;

import edu.hhuc.yixiang.common.dto.UserDetailDTO;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 15:50:04
 */
public interface UserService {
    UserDetailDTO queryUserDetail(String userIdOrNickName);
}
