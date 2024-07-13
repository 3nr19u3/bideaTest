package com.bideafactory.booking.dto;

import com.bideafactory.booking.enums.BookingStatus;

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
}
