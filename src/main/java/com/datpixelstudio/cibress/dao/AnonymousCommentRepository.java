package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.AnonymousComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousCommentRepository extends JpaRepository<AnonymousComment, Long> {
    AnonymousComment findById(long id); // TODO need to now how to handle the Optional; just forced right type here
}
