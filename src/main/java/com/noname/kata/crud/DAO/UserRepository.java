package com.noname.kata.crud.DAO;

import com.noname.kata.crud.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
}
