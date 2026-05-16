package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.Users;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @ParameterizedTest()
    @ArgumentsSource(UserArgumentProvider.class)
    public void testFindUserByUserName(Users user)
    {
        assertTrue(userService.saveNewUser(user));
    }
}
