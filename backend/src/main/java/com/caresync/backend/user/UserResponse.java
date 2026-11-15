package com.caresync.backend.user;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email, String phone) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }
}
