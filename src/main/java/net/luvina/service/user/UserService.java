package net.luvina.service.user;

import net.luvina.model.User;

public interface UserService {
    User authenticate(User user);
}
