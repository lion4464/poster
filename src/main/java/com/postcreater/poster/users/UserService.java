package com.postcreater.poster.users;

import com.postcreater.poster.exceptions.BadCredentialsException;
import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.exceptions.UserAlreadyExistsException;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

 Optional<UserEntity> findByUsername(String username);

    void register(RegisterRequest registerRequest) throws UserAlreadyExistsException;

    AuthResponse  login(LoginRequest request) throws BadCredentialsException;

    UserEntity findById(UUID userId) throws DataNotFoundException;

    UserEntity save(UserEntity user);
}
