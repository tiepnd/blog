package net.luvina.dao.comment;

import net.luvina.model.Comment;
import net.luvina.model.Post;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll();
    Comment findById(Long id);
    Comment insert(Comment comment);
    int update(Comment comment);
    int delete(Comment comment);
    int deleteByPostId(Comment comment);
    int count();
}
