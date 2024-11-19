package com.postcreater.poster.admin;

import com.postcreater.poster.exceptions.DataNotFoundException;
import com.postcreater.poster.users.UserEntity;
import com.postcreater.poster.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserService userService;
    @Override
    public UserEntity changeUserRole(UUID userId, ChangeRoleRequest request) throws DataNotFoundException {
        UserEntity user = userService.findById(userId);
        user.setRole(request.getRole());
        return userService.save(user);
    }
}
