package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.playground.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Iterable<Message> findByUserId(Long id);

    @PostAuthorize("hasPermission(returnObject, 'privateMessage')")
    Optional<Message> findById(Long id);

//    @PostAuthorize("hasPermission(returnObject, 'privateMessage')")
//    Message findOne(Long id);
}
