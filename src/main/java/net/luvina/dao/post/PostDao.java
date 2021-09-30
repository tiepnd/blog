package net.luvina.dao.post;

import net.luvina.mapper.RowMapper;
import net.luvina.model.Post;
import net.luvina.paging.Pageable;
import net.luvina.paging.Sortable;

import java.util.List;

public interface PostDao {
    List<Post> findAll();
    List<Post> findAll(Pageable pageable, Sortable sortable);
    Post findById(Long id);
    Post insert(Post post);
    int update(Post post);
    int delete(Post post);
    Long count();
}
