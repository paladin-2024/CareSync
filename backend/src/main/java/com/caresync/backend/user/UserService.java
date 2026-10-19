package com.caresync.backend.user;

import com.caresync.backend.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(CreateUserRequest request) {
        return userRepository.findByEmail(request.email())
                .orElseGet(() -> userRepository.save(new User(request.name(), request.email(), request.phone())));
    }

    public User get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
    }
}
