package com.datpixelstudio.cibress;

import com.datpixelstudio.cibress.dao.MessageRepository;
import com.datpixelstudio.cibress.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void getMessageWithoutUser() {
        messageRepository.findById(1l);
    }

    @Test
    @WithUserDetails(value = "staxx6")
    public void getMessage() {
        messageRepository.findById(1l);
    }

    public void getMessageFromAnotherUser() {
        try {
            messageRepository.findById(1l);
            assertTrue("staxx6 can see the message of root by findById", false);
        } catch (AccessDeniedException e) {
        }
    }
}
