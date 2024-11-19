package com.postcreater.poster.admin;

import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.generic.ApiResponse;
import com.postcreater.poster.users.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<ApiResponse<UserEntity>> changeUserRole(
            @PathVariable UUID userId,
            @Valid @RequestBody ChangeRoleRequest request) throws DataNotFoundException {
        return ApiResponse.ok(
                adminService.changeUserRole(userId, request),
                "User role updated successfully"
        );
    }
}