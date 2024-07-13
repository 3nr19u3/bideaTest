package com.bideafactory.booking.mapper;

import com.bideafactory.booking.dto.UserDto;
import com.bideafactory.booking.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getAge(),
                user.getPhoneNumber()
        );
    }

    public static User mapToUse(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.lastName(),
                userDto.age(),
                userDto.phoneNumber(),
                null
        );
    }
}
