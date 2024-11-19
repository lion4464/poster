package com.postcreater.poster.users;

import com.postcreater.poster.exceptions.BadCredentialsException;
import com.postcreater.poster.exceptions.UserAlreadyExistsException;
import com.postcreater.poster.generic.ApiResponse;
import com.postcreater.poster.generic.GenericResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        userService.register(registerRequest);
        return ResponseEntity.ok(new GenericResponse("User registered successfully!",true));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) throws BadCredentialsException {
        return ApiResponse.ok(userService.login(request), "Login successful");
    }
}
