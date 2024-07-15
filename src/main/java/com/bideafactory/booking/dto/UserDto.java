package com.bideafactory.booking.dto;

import com.bideafactory.booking.exception.BadRequestException;

import java.util.UUID;

public record UserDto (
        UUID id,
        String name,
        String lastName,
        int age,
        String phoneNumber
){
    //regular expresion for numebr phone validate
    static String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    public UserDto {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("the name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new BadRequestException("the lastName cannot be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() != 9 ) {
            throw new BadRequestException("the phoneNumber cannot be null or empty");
        }

        if (age <= 17) {
            throw new BadRequestException("You should have at least 17 years old");
        }

        if(pattern.matches(pattern)){
            throw new BadRequestException("invalid phoneNumber");
        }
    }
}
