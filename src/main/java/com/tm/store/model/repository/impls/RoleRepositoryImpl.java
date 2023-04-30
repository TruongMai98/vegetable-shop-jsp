package com.tm.store.model.repository.impls;

import com.tm.store.configuratiuion.DataConnection;
import com.tm.store.model.entity.Role;
import com.tm.store.model.repository.IRoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepositoryImpl implements IRoleRepository {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private static final String SELECT_BY_NAME_ROLE = "SELECT * FROM roles WHERE name = ?";

    public RoleRepositoryImpl() throws SQLException, ClassNotFoundException {
        connection = DataConnection.getConnection();
    }

    @Override
    public Role findByName(String name) {
        Role role = null;
        try  {
            preparedStatement = connection.prepareStatement(SELECT_BY_NAME_ROLE);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}

