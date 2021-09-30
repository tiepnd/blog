package net.luvina.service.category;

import net.luvina.dao.category.CategoryDao;
import net.luvina.model.Category;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {

        return categoryDao.findById(1);
    }

    @Override
    public int insertCategory(String sql, Object... parameters) {
        return categoryDao.insertCategory("insert into category (name, code) values (?, ?)", "ReactJs", "reactjs").intValue();
    }
}
