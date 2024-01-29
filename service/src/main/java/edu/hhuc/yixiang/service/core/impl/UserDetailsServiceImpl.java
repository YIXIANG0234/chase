package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.dto.UserDetailDTO;
import edu.hhuc.yixiang.service.core.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/21 21:30:38
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailDTO userDetailDTO = userService.queryUserDetail(username);
        if (Objects.isNull(userDetailDTO)) {
            return null;
        }
        return new org.springframework.security.core.userdetails.User(userDetailDTO.getNickName(), userDetailDTO.getPassword(), AuthorityUtils.createAuthorityList(userDetailDTO.authority()));
    }
}
