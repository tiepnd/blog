package net.luvina.service.user;

import net.luvina.dao.user.UserDao;
import net.luvina.model.User;

import javax.inject.Inject;

public class UserServiceImpl implements UserService{
    @Inject
    private UserDao userDao;
    @Override
    public User authenticate(User user) {
        return userDao.findByUserNameAndPasswordAndStatus(user.getUserName(), user.getPassword(), 1);
    }
}
