package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
