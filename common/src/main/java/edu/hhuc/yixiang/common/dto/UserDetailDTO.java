package edu.hhuc.yixiang.common.dto;

import com.google.common.collect.Lists;
import edu.hhuc.yixiang.common.entity.User;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/26 14:28:40
 */
@Data
public class UserDetailDTO {

    private String userId;

    private String nickName;

    private String password;

    private String salt;

    private String status;

    private Date recentLoginTime;

    private Long loginCount;

    private List<UserGroupDTO> userGroups;

    public UserDetailDTO(User user, List<UserGroupDTO> userGroups) {
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.password = user.getPassword();
        this.salt = user.getSalt();
        this.status = user.getStatus();
        this.recentLoginTime = user.getRecentLoginTime();
        this.loginCount = user.getLoginCount();
        this.userGroups = userGroups;
    }

    public UserDetailDTO() {
    }

    public List<String> authority() {
        if (CollectionUtils.isEmpty(this.getUserGroups())) {
            return Lists.newArrayList();
        }
        return this.getUserGroups()
                .stream()
                .flatMap(group -> group.getRoles().stream().flatMap(role -> role.getAuthorities().stream()))
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> roles() {
        if (CollectionUtils.isEmpty(this.getUserGroups())) {
            return Lists.newArrayList();
        }
        return this.getUserGroups()
                .stream()
                .flatMap(group -> group.getRoles().stream())
                .map(UserRoleDTO::getRoleCode)
                .distinct()
                .collect(Collectors.toList());
    }
}
