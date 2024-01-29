package edu.hhuc.yixiang.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 14:35:08
 */
@Data
public class UserRoleDTO {
    private Long id;
    private String parentId;
    private String roleName;
    private String roleCode;
    private List<String> authorities;
}
