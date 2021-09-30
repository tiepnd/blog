package net.luvina.service.post;

import net.luvina.dao.comment.CommentDao;
import net.luvina.dao.post.PostDao;
import net.luvina.model.Comment;
import net.luvina.model.Post;
import net.luvina.paging.Pageable;
import net.luvina.paging.Sortable;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
public class PostServiceImpl implements PostService {
    @Inject
    private PostDao postDao;

    @Inject
    private CommentDao commentDao;

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public List<Post> findAll(Pageable pageable, Sortable sortable) {
        return postDao.findAll(pageable, sortable);
    }

    @Override
    public Post findById(Long id) {
        return postDao.findById(id);
    }

    @Override
    public Post insert(Post post) {
        post.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return postDao.insert(post);
    }

    @Override
    public boolean update(Post post) {
        post.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        return postDao.update(post) == 0 ? false : true;
    }

    @Override
    public boolean delete(Long[] ids) {
        for (Long id : ids
        ) {
            System.out.println("id" + id);
            Post post = new Post(id);
            Comment comment = new Comment(id);
            commentDao.deleteByPostId(comment);
            postDao.delete(post);
        }
        return true;
    }

    @Override
    public Long count() {
        return postDao.count();
    }
}
