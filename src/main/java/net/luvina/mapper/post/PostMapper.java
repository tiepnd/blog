package net.luvina.mapper.post;

import net.luvina.mapper.RowMapper;
import net.luvina.model.Category;
import net.luvina.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {

    @Override
    public Post rowMap(ResultSet resultSet) {
        try {
            return new Post(resultSet.getLong("id"), resultSet.getString("title"),
                    resultSet.getString("thumbnail"), resultSet.getString("shortdescription"),
                    resultSet.getString("content"), resultSet.getLong("categoryid"),
                    resultSet.getTimestamp("createddate"), resultSet.getString("createdby"));

        } catch (SQLException exception) {
            System.out.println("PostMapper:rowMap: " + exception.getMessage());
        }
        return null;
    }
}
