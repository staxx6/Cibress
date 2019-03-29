package com.datpixelstudio.cibress.dao;

import com.datpixelstudio.cibress.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
//    Unit findById(Long id);
}
