package net.luvina.dao.base;

import net.luvina.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;

public interface BaseDao<T> {
    List<T> query(String sqlQuery, RowMapper<T> rowMapper , Object... parameters);
    int update(String sqlQuery, Object... parameters);
    Long insert(String sqlQuery, Object... parameters);
    Long count(String sqlQuery);
}
