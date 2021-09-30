package net.luvina.dao.comment;

import net.luvina.dao.base.BaseDaoImpl;
import net.luvina.model.Comment;

import java.util.List;

public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

    @Override
    public Comment insert(Comment comment) {
        return null;
    }

    @Override
    public int update(Comment comment) {
        return 0;
    }

    @Override
    public int delete(Comment comment) {
        return super.update("delete from comment where id = ?", new Object[]{comment.getId()});
    }

    @Override
    public int deleteByPostId(Comment comment) {
        return super.update("delete from comment where postid = ?", new Object[]{comment.getPostId()});
    }

    @Override
    public int count() {

        return 0;
    }
}
