package net.luvina.dao.category;

import net.luvina.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    Category findById(int id);
    Long insertCategory(String sql, Object... parameters);
}
