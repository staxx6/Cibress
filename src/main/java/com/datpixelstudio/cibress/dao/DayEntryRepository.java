package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.Dish;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DayEntryRepository extends JpaRepository<DayEntry, Long> {
    DayEntry findByUserAndEntryRecord(User user, Date entryRecord);
}

