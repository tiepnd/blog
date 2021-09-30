package net.luvina.service.post;

import net.luvina.model.Post;
import net.luvina.paging.Pageable;
import net.luvina.paging.Sortable;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> findAll(Pageable pageable, Sortable sortable);

    Post findById(Long id);

    Post insert(Post post);

    boolean update(Post post);

    boolean delete(Long[] ids);

    Long count();
}
