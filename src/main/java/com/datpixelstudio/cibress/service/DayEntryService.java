package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.User;

import java.util.Date;

public interface DayEntryService {
    DayEntryDto findByDate(User user, Date day);
}
