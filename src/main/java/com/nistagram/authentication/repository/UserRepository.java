package com.nistagram.authentication.repository;

import com.nistagram.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String user);
}
