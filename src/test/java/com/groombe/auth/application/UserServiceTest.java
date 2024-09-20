package com.groombe.auth.application;

import com.groombe.auth.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User student;
    private User admin;

    @BeforeEach
    public void setUp() {
        student = new User("student1", "password123", "STUDENT");
        admin = new User("admin1", "adminpass", "ADMIN");
    }

    @Test
    public void testStudentRegistration() {
        User registeredStudent = userService.registerUser(student);
        assertNotNull(registeredStudent);
        assertEquals("student1", registeredStudent.getUsername());
    }

    @Test
    public void testAdminRegistration() {
        User registeredAdmin = userService.registerUser(admin);
        assertNotNull(registeredAdmin);
        assertEquals("admin1", registeredAdmin.getUsername());
    }

    @Test
    public void testUserLoginSuccess() {
        userService.registerUser(student);
        User loggedInUser = userService.login("student1", "password123");
        assertNotNull(loggedInUser);
        assertEquals("student1", loggedInUser.getUsername());
    }

    @Test
    public void testUserLoginFailure() {
        User loggedInUser = userService.login("nonexistent", "wrongpassword");
        assertNull(loggedInUser);
    }
}