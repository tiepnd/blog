package net.luvina.mapper.user;

import net.luvina.mapper.RowMapper;
import net.luvina.model.Role;
import net.luvina.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User rowMap(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFullName(resultSet.getString("fullname"));
            user.setStatus(resultSet.getInt("status"));
            Role role = new Role();
            try{
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
                user.setRole(role);
            }catch (SQLException exception){
                System.out.println("UserMapper:rowMap " + exception.getMessage());
            }
        } catch (SQLException exception) {
            System.out.println("UserMapper:rowMap " + exception.getMessage());
        }
        return user;
    }
}
