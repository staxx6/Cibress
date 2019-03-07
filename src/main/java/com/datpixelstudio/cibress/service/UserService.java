package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dto.UserDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.exception.EmailExistsException;

public interface UserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
