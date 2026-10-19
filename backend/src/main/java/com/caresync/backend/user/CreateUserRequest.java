package com.caresync.backend.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank @Size(min = 2, max = 50) String name,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "^\\+\\d{10,15}$", message = "Invalid phone number") String phone
) {
}
