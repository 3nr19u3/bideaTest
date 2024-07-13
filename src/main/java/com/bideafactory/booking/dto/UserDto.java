package com.bideafactory.booking.dto;

import java.util.UUID;

public record UserDto (
        UUID id,
        String name,
        String lastName,
        int age,
        Long phoneNumber
){
}
