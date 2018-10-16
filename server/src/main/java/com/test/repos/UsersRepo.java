package com.test.repos;

import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
