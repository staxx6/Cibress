package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.UserRepository;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("The user " + username + " does nit exist");
        }
        return new AuthenticatedUser(user);
    }
}
