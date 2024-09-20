package com.groombe.auth.application;

import com.groombe.auth.domain.Role;
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
        student = new User("student1", "password123", Role.USER);
        admin = new User("admin1", "adminpass", Role.ADMIN);
    }

    // 학생 유저 등록 테스트
    @Test
    public void testStudentRegistration() {
        User registeredStudent = userService.registerUser(student);
        assertNotNull(registeredStudent);
        assertEquals("student1", registeredStudent.getUsername());
    }

    // 관리자 유저 등록 테스트
    @Test
    public void testAdminRegistration() {
        User registeredAdmin = userService.registerUser(admin);
        assertNotNull(registeredAdmin);
        assertEquals("admin1", registeredAdmin.getUsername());
    }

    // 중복된 사용자 이름으로 등록 시도
    @Test
    public void testUserLoginSuccess() {
        userService.registerUser(student);
        User loggedInUser = userService.login("student1", "password123");
        assertNotNull(loggedInUser);
        assertEquals("student1", loggedInUser.getUsername());
    }

    // 존재하지 않는 사용자 이름으로 로그인 시도
    @Test
    public void testUserLoginFailure() {
        User loggedInUser = userService.login("nonexistent", "wrongpassword");
        assertNull(loggedInUser);
    }

    // 비밀번호가 틀린 경우 로그인 실패
    @Test
    public void testUserLoginFailureWithWrongPassword() {
        userService.registerUser(student);
        User loggedInUser = userService.login("student1", "wrongpassword");
        assertNull(loggedInUser);
    }
}