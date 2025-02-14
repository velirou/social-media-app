package com.socialmedia.userservice;

import com.socialmedia.userservice.model.User;
import com.socialmedia.userservice.repository.UserRepository;
import com.socialmedia.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "alex123", "sarah.jones@example.com");
        User user2 = new User(2L, "jenny_34", "jane.smith@example.com");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("alex123", result.get(0).getUsername());
        assertEquals("jenny_34", result.get(1).getUsername());

        verify(userRepository, times(1)).findAll();
    }
}
