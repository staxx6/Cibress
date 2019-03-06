package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.playground.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Iterable<Message> findByUserId(Long id);
//    Message findById(Long id);
}
