package com.bideafactory.booking.dto;

import java.util.UUID;

public record VerifyDiscountServiceRequest(
        UUID userId,
        UUID houseId,
        String discountCode
) {
}
