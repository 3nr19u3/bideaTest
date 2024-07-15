package com.bideafactory.booking.dto;

import com.bideafactory.booking.enums.Status;
import com.bideafactory.booking.exception.BadRequestException;

import java.util.UUID;

public record HouseDto(
        UUID uuid,
        String name,
        double price,
        Status status
) {
    public HouseDto {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("the name house cannot be null or empty");
        }

        if (price < 50) {
            throw new BadRequestException("the price basic is 50 for rent house");
        }
        if (status == null) {
            throw new BadRequestException("the status house cannot be null");
        }
    }
}
