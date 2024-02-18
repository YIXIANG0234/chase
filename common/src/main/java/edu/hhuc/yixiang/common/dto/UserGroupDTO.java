package edu.hhuc.yixiang.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 14:34:29
 */
@Data
public class UserGroupDTO {
    private Long id;
    private String groupName;
    private List<UserRoleDTO> roles;
}
