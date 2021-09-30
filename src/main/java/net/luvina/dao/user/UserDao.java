package net.luvina.dao.user;

import net.luvina.model.User;

public interface UserDao {
    User findByUserNameAndPasswordAndStatus(String userName, String password, int status);
}
