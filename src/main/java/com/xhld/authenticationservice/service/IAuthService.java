package com.xhld.authenticationservice.service;

import com.lzh.common.model.entity.auth.UserDto;

public interface IAuthService {
    UserDto register(UserDto userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
