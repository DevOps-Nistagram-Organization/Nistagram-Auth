package com.nistagram.authentication.repository;

import com.nistagram.authentication.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String user);
}
