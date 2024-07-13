package com.bideafactory.booking.service;

import com.bideafactory.booking.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto getUserById(UUID userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UUID userId, UserDto userDto);

    void deleteUser(UUID userId);
}
