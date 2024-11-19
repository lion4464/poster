package com.postcreater.poster.admin;

import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.users.UserEntity;

import java.util.UUID;

public interface AdminService {
    UserEntity changeUserRole(UUID userId, ChangeRoleRequest request) throws DataNotFoundException;
}
