package com.tm.store.model.service;

import com.tm.store.model.dto.UserDto;

public interface IUserService extends IGeneralService<UserDto>{
    UserDto login(UserDto userDto);
}
