package com.noname.kata.crud.DAO;


import com.noname.kata.crud.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void removeById(Long id);
    void saveNewUserProfile(User user);
    User getById(Long id);
    Boolean isUserExistsWithEmail(String email);
    void updateUserProfile(User user);



}
