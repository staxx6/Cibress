package com.datpixelstudio.cibress.security;

import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.playground.Message;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
public class SimplePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        System.out.println("Works here: " + authentication + " " + targetDomainObject + " " + permission);
        if(authentication == null) return false;

        Message message = ((Optional<Message>) targetDomainObject).get();
        if(message == null) return true;

        User user = (User) authentication.getPrincipal();

        if(user.getId() == message.getUser().getId())
            return true;

        if("privateMessage".equals(permission)) {
            return false;
        } else if("message".equals(permission) && "ROLE_ADMIN".equals(user.getRole())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
