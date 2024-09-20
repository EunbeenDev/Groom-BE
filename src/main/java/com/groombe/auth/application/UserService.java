package com.groombe.auth.application;


import com.groombe.auth.domain.User;
import com.groombe.auth.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User registerUser(User user) {
        // 중복된 사용자 이름 확인
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        // 새 사용자 저장
        User newUser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(loginRequest.getRole())
                .build();

        return userRepository.save(newUser);
    }

    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        throw new RuntimeException("사용자 이름 또는 비밀번호가 잘못되었습니다.");
    }
}
