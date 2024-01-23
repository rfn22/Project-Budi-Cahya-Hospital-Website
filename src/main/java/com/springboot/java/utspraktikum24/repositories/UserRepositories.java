package com.springboot.java.utspraktikum24.repositories;

import com.springboot.java.utspraktikum24.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}

