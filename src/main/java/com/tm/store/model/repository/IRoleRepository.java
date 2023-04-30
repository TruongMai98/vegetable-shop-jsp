package com.tm.store.model.repository;

import com.tm.store.model.entity.Role;

public interface IRoleRepository {
    Role findByName(String name);
}
