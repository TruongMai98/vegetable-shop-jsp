package com.tm.store.model.service;

import com.tm.store.model.dto.RoleDto;


public interface IRoleService extends IGeneralService<RoleDto>{
    RoleDto findByName(String name);
}
