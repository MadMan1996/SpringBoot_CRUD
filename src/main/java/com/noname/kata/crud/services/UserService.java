package com.noname.kata.crud.services;


import com.noname.kata.crud.models.User;

import java.util.List;

public interface UserService {
    Iterable<User> getAllUsers();
    void removeUserById(Long id);
    void saveNewUserProfile(User user);
    User getById(Long id);

    void updateUserProfile(User user);

   Boolean isUserExistsWithEmail(String email);

}
