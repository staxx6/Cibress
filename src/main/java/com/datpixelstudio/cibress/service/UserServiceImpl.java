package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.UserRepository;
import com.datpixelstudio.cibress.dto.UserDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    // is it in repo?
//    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if(emailExists(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "
            + accountDto.getEmail());
        }

        User user = new User();
        user.setId(0);
        user.setUsername(accountDto.getFirstName());
        user.setEmail(accountDto.getEmail());
        user.setPassword("{noop}" + accountDto.getPassword());
        user.setRole("ROLE_USER");
        repository.save(user); // returns a user?

        return user;
    }

    private boolean emailExists(String email) {
        User user = repository.findByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }
}
