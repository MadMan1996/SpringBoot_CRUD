package com.noname.kata.crud.services;

import com.noname.kata.crud.DAO.UserRepository;
import com.noname.kata.crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public Iterable<User> getAllUsers() {
       return userRepository.findAll();
    }
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public void saveNewUserProfile(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Transactional
    public void updateUserProfile(User user) {
        userRepository.save(user);

    }


    @Transactional(readOnly = true)
    public Boolean isUserExistsWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
