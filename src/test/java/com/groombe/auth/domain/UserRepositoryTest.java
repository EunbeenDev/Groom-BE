package com.groombe.auth.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 테스트용 사용자 저장 및 조회
    @Test
    public void testSaveAndFindUser() {

        // Given
        // 사용자 생성
        User user = new User("john", "password123", Role.USER);
        userRepository.save(user);

        // When
        // 사용자 이름으로 사용자 조회
        Optional<User> foundUser = userRepository.findByUsername("john");

        // Then
        // 사용자가 존재하는지 확인
        assertTrue(foundUser.isPresent());
        assertEquals("john", foundUser.get().getUsername());
    }

    // 존재하지 않는 사용자 조회
    @Test
    public void testFindNonExistingUser() {
        Optional<User> foundUser = userRepository.findByUsername("nonexistinguser");
        assertFalse(foundUser.isPresent());
    }
}