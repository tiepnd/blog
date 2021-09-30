package net.luvina.dao.category;

import net.luvina.dao.base.BaseDaoImpl;
import net.luvina.mapper.category.CategoryMapper;
import net.luvina.model.Category;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao{

    @Override
    public List<Category> findAll() {
        return query("select * from category", new CategoryMapper(), new Object[]{});
    }
    @Override
    public Category findById(int id) {
        List<Category> categoryList = query("select * from category where id = ?", new CategoryMapper(), new Object[]{id});
        return categoryList.isEmpty()?null:categoryList.get(0);
    }

    @Override
    public Long insertCategory(String sql, Object... parameters) {
        return insert(sql,parameters);
    }

}
