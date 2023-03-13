package org.shruthipattanasetty.foodbankdonation.services;

import org.shruthipattanasetty.foodbankdonation.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user);

    User getUserByEmailAndPassword(String email, String password);

    User getUserById(int id) throws Exception;

    User getUserByEmail(String email);
}
