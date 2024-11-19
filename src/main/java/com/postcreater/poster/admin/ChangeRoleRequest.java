package com.postcreater.poster.admin;

import com.postcreater.poster.users.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeRoleRequest {
    @NotNull(message = "Role is required")
    private Role role;
}
