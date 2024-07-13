package com.bideafactory.booking.controller;

import com.bideafactory.booking.dto.BookingDto;
import com.bideafactory.booking.dto.HouseDto;
import com.bideafactory.booking.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        BookingDto bookingDto1 = bookingService.registerBooking(bookingDto);
        return new ResponseEntity<>(bookingDto1, HttpStatus.CREATED);
    }
}
