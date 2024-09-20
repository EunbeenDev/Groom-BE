package com.groombe.auth.dto.request;

import com.groombe.auth.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {
    private String username;
    private String password;
    private Role role;
}
