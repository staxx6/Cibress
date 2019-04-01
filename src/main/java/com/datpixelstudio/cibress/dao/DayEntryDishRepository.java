package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayEntryDishRepository extends JpaRepository<DayEntryDish, Long> {
}
