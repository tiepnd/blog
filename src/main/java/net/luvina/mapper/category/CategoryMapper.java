package net.luvina.mapper.category;

import net.luvina.mapper.RowMapper;
import net.luvina.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category rowMap(ResultSet resultSet) {
        try {
            return new Category(resultSet.getLong("id"),resultSet.getString("name"), resultSet.getString("code"));
        } catch (SQLException exception) {
            System.out.println("CategoryMapper:rowMap: " + exception.getMessage());
        }
        return null;
    }
}
