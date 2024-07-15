package com.bideafactory.booking.dto;

import com.bideafactory.booking.enums.BookingStatus;
import com.bideafactory.booking.exception.BadRequestException;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingDto(
        UUID id,
        UUID userId,
        UUID houseId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String discountCode,
        double value,
        double finalValue,
        BookingStatus status
) {
    public BookingDto {
        if (userId == null) {
            throw new BadRequestException("the userId cannot be null or empty");
        }
        if (houseId == null) {
            throw new BadRequestException("the houseId cannot be null or empty");
        }
        if (startDate == null) {
            throw new BadRequestException("the startDate cannot be null or empty");
        }
        if (endDate == null) {
            throw new BadRequestException("the endDate cannot be null or empty");
        }
        if (discountCode == null || discountCode.isEmpty() || discountCode.isBlank()) {
            throw new BadRequestException("the discountCode cannot be null or empty");
        }

    }
}
