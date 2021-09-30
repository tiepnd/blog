package net.luvina.dao.user;

import net.luvina.dao.base.BaseDaoImpl;
import net.luvina.mapper.user.UserMapper;
import net.luvina.model.User;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findByUserNameAndPasswordAndStatus(String userName, String password, int status) {
        StringBuilder sqlQuery = new StringBuilder("select * from users as u inner join role r on u.roleid = r.id ");
        sqlQuery.append("where u.username = ? and u.password = ? and u.status = ?");
        List<User> users = super.query(sqlQuery.toString(), new UserMapper(), new Object[]{userName, password, status});
        return users.isEmpty() ? null : users.get(0);
    }
}
