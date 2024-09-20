package com.groombe.auth.domain;


import com.groombe.auth.domain.User;
import com.groombe.auth.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        // Given
        User user = new User("john", "password123", "STUDENT");
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByUsername("john");

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("john", foundUser.get().getUsername());
    }

    @Test
    public void testFindNonExistingUser() {
        Optional<User> foundUser = userRepository.findByUsername("nonexistinguser");
        assertFalse(foundUser.isPresent());
    }
}