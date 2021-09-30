package net.luvina.dao.post;

import net.luvina.dao.base.BaseDaoImpl;
import net.luvina.mapper.post.PostMapper;
import net.luvina.model.Post;
import net.luvina.paging.Pageable;
import net.luvina.paging.Sortable;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao {

    @Override
    public List<Post> findAll() {
        return query("select * from post", new PostMapper(), new Object[]{});
    }

    @Override
    public List<Post> findAll(Pageable pageable, Sortable sortable) {
        StringBuilder sqlQuery = new StringBuilder("select * from post ");
        Object[] parameters = new Object[10];
        int i = 0;
        if (sortable != null && sortable.getSortName() != null) {
            sqlQuery.append("order by ");
            sqlQuery.append(sortable.getSortName() + " " + sortable.getSortType());
        }
        if (pageable != null) {
            if (pageable.getLimit() != null) {
                sqlQuery.append(" limit ? ");
                parameters[i++] = pageable.getLimit();
            }
            if (pageable.getOffset() != null) {
                sqlQuery.append("offset ? ");
                parameters[i++] = pageable.getOffset();
            }
        }
        return query(sqlQuery.toString(), new PostMapper(), parameters);
    }

    @Override
    public Post findById(Long id) {
        List<Post> postList = query("select * from post where id = ?;", new PostMapper(), new Object[]{id});
        return postList.isEmpty() ? null : postList.get(0);
    }

    @Override
    public Post insert(Post post) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into post ");
        sql.append("(title, thumbnail, shortdescription, content, categoryId, createddate, createdby) ");
        sql.append("values (?, ?, ?, ?, ?, ?, ?);");

        Long id = super.insert(sql.toString(), new Object[]{post.getTitle(), post.getThumbnail(), post.getShortDescription(),
                post.getContent(), post.getCategoryId(), post.getCreatedDate(), post.getCreatedBy()});
        post.setId(id);
        return post;
    }

    @Override
    public int update(Post post) {
        StringBuilder sql = new StringBuilder();
        sql.append("update post set ");
        sql.append("title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryId = ?, modifieddate = ?, modifiedby = ? ");
        sql.append("where id = ?;");
        return super.update(sql.toString(), new Object[]{post.getTitle(), post.getThumbnail(), post.getShortDescription(),
                post.getContent(), post.getCategoryId(), post.getModifiedDate(), post.getModifiedBy(), post.getId()});
    }

    @Override
    public int delete(Post post) {
        return super.update("delete from post where id = ?", new Object[]{post.getId()});
    }

    @Override
    public Long count() {
        return super.count("select count(1) from post");
    }
}
