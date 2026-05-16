package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.Users;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceimpliTests {
    @InjectMocks
    private userdetailserviceimpl userdetailserviceimpl;

    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    void loadByUsernametest()
    {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn((Users.builder().username("John Doe").password("hello").roles(new ArrayList<>()).build()));
        UserDetails user = userdetailserviceimpl. loadUserByUsername("John Doe");
        Assertions.assertNotNull(user);
    }
}
