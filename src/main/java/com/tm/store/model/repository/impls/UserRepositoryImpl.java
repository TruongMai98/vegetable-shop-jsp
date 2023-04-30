package com.tm.store.model.repository.impls;

import com.tm.store.configuratiuion.DataConnection;
import com.tm.store.model.dto.RoleDto;
import com.tm.store.model.entity.Role;
import com.tm.store.model.entity.User;
import com.tm.store.model.repository.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepositoryImpl implements IUserRepository {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String SELECT_BY_ID_USER = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_BY_EMAIL_USER = "SELECT u.id, u.name, u.email, u.password, r.name AS role_name FROM users u JOIN users_roles ur ON u.id = ur.user_id JOIN roles r ON ur.role_id = r.id WHERE u.email = ?";
    private static final String CREATE_USER = "INSERT INTO users(name, email, password, is_deleted) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "UPDATE users SET is_deleted = true WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ? AND is_deleted = 0";

    public UserRepositoryImpl() throws SQLException, ClassNotFoundException {
        connection = DataConnection.getConnection();
    }

    @Override
    public Iterable<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_USER);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOptional;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_USER);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                Set<Role> roles = new HashSet<>();
                roles.add(new Role(resultSet.getString("role_name")));
                user.setRoles(roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
