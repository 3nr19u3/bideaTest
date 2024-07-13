package com.bideafactory.booking.mapper;

import com.bideafactory.booking.dto.BookingDto;
import com.bideafactory.booking.entity.Booking;
import com.bideafactory.booking.enums.BookingStatus;

public class BookingMapper {
    public static BookingDto mapToBookingDto(Booking booking) {
        return new BookingDto(
                booking.getId(),
                booking.getUser() != null ? booking.getUser().getId() : null,
                booking.getHouse() != null ? booking.getHouse().getId() : null,
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getDiscountCode(),
                booking.getValue(),
                booking.getFinalValue(),
                booking.getStatus()
        );
    }

    /*public static Booking mapToBooking(BookingDto bookingDto) {
        return new Booking(
                bookingDto.id(),
                bookingDto.discountCode(),
                bookingDto.startDate(),
                bookingDto.endDate(),
                bookingDto.value(),
                bookingDto.finalValue(),
                bookingDto.status(),
                bookingDto.user(),
                bookingDto.house()
        );
    }*/
}
