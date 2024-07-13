package com.bideafactory.booking.dto;

import java.util.UUID;

public record VerifyDiscountServiceResponse(
        UUID houseId,
        String discountCode,
        int id,
        UUID userId,
        boolean status
){
}
