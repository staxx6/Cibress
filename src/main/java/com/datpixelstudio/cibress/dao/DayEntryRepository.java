package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface DayEntryRepository extends JpaRepository<DayEntry, Long> {
    DayEntry findByUserAndEntryRecord(User user, LocalDate entryRecord);
}

