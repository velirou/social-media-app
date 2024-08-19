package com.socialmedia.userservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.socialmedia.userservice.model.User;
import com.socialmedia.userservice.repository.UserRepository;
import com.socialmedia.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
        User user1 = new User(1L, "alex123", "sarah.jones@example.com", "$2a$10$V6v2wq4JQFV2mQzLWZj2Ou", Timestamp.valueOf("2009-10-13 16:09:23"), Timestamp.valueOf("2009-09-15 12:07:45"));
        User user2 = new User(2L, "jenny_34", "jane.smith@example.com", "$2a$10$QK3b7pQh6XZ2mQzLWZj2Ou", Timestamp.valueOf("2009-10-04 20:00:24"), Timestamp.valueOf("2008-01-12 13:49:03"));
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