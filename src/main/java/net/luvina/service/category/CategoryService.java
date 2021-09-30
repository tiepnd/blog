package net.luvina.service.category;

import net.luvina.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    int insertCategory(String sql, Object... parameters);
}
