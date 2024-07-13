package com.bideafactory.booking.dto;

import com.bideafactory.booking.enums.Status;

import java.util.UUID;

public record HouseDto(
        UUID uuid,
        String name,
        double price,
        Status status
) {
}
