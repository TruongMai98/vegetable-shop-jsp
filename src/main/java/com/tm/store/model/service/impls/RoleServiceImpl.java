package com.tm.store.model.service.impls;

import com.tm.store.model.dto.RoleDto;
import com.tm.store.model.dto.UserDto;
import com.tm.store.model.entity.Role;
import com.tm.store.model.repository.IRoleRepository;
import com.tm.store.model.repository.impls.RoleRepositoryImpl;
import com.tm.store.model.service.IRoleService;

import java.sql.SQLException;
import java.util.Optional;

public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    public RoleServiceImpl() throws SQLException, ClassNotFoundException {
        this.roleRepository = new RoleRepositoryImpl();
    }

    @Override
    public Iterable<RoleDto> findAll() {
        return null;
    }

    @Override
    public Optional<RoleDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(RoleDto roleDto) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public RoleDto findByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setName(role.getName());
            return roleDto;
        } else {
            return null;
        }
    }
}
