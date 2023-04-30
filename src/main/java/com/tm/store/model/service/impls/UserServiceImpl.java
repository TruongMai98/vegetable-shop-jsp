package com.tm.store.model.service.impls;

import com.tm.store.model.dto.UserDto;
import com.tm.store.model.entity.User;
import com.tm.store.model.repository.IUserRepository;
import com.tm.store.model.repository.impls.UserRepositoryImpl;
import com.tm.store.model.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl() {
        try {
            this.userRepository = new UserRepositoryImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<UserDto> findAll() {
        return null;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(UserDto userDto) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public UserDto login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null && BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            UserDto authenticatedUser = new UserDto();
            authenticatedUser.setName(user.getName());
            authenticatedUser.setEmail(user.getEmail());
            authenticatedUser.setPassword(user.getPassword());
            authenticatedUser.setRoles(user.getRoles());
            return authenticatedUser;
        } else {
            return null;
        }
    }
}
