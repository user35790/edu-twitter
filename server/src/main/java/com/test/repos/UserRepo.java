package com.test.repos;

import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findFirstById(int id);

    User findByActivationCode(String code);
}
