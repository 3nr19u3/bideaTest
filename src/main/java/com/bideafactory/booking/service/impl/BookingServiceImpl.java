package com.bideafactory.booking.service.impl;

import com.bideafactory.booking.dto.BookingDto;
import com.bideafactory.booking.dto.VerifyDiscountServiceRequest;
import com.bideafactory.booking.dto.VerifyDiscountServiceResponse;
import com.bideafactory.booking.entity.Booking;
import com.bideafactory.booking.entity.House;
import com.bideafactory.booking.entity.User;
import com.bideafactory.booking.enums.BookingStatus;
import com.bideafactory.booking.enums.Status;
import com.bideafactory.booking.exception.APIException;
import com.bideafactory.booking.exception.BadRequestException;
import com.bideafactory.booking.exception.ResourceNotFoundException;
import com.bideafactory.booking.mapper.BookingMapper;
import com.bideafactory.booking.repository.BookingRepository;
import com.bideafactory.booking.repository.HouseRepository;
import com.bideafactory.booking.repository.UserRepository;
import com.bideafactory.booking.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private HouseRepository houseRepository;

    @Value("${external.service.url}")
    private String EXTERNAL_SERVICE_URL;

    @Autowired
    private WebClient webClient;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              HouseRepository houseRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public BookingDto registerBooking(BookingDto bookingDto) {
        int dias;
        LocalDateTime startDay = bookingDto.startDate();
        LocalDateTime endDay = bookingDto.endDate();
        //Validate that the start date is greater than the end date.
        if(startDay.isAfter(endDay))
            throw new BadRequestException("Inconsistency error between days");
        else
            //Get the difference number between the days
             dias = (int)Duration.between(startDay, endDay).toDays();

        if(dias < 1)
            throw new BadRequestException("Days range invalid!");

        //validate user exist
        User user = userRepository.findById(bookingDto.userId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", bookingDto.userId()));
        LOGGER.debug("user exists : {}", user.toString());
        //if the user exist the process continue ...
        //validate house exist and be available in period days requested
        House house = houseRepository.findById(bookingDto.houseId()).orElseThrow(() -> new ResourceNotFoundException("House", "id", bookingDto.houseId()));
        LOGGER.debug("house status : {}", house.getStatus());
        //validate if house is avalable
        if(house.getStatus().equals(Status.UNAVAILABLE))
            throw new BadRequestException("The house requested is unavailable");

        //validate discountCode : throw HTTP ERROR 409 code if not valid
        VerifyDiscountServiceRequest request = new VerifyDiscountServiceRequest(bookingDto.userId(),bookingDto.houseId(),bookingDto.discountCode());
        LOGGER.debug("request : {}", request);
        //boolean isValidDiscountCode = verifyDiscountCode(request);
        VerifyDiscountServiceResponse response = verifyDiscountCode(request);
        if(response == null) {
            LOGGER.error("External service error");
            throw new APIException(HttpStatus.CONFLICT, "Service Unavailable");
        }

        boolean isValidDiscountCode = response.status();
        LOGGER.debug("isValidDiscountCode : {}", isValidDiscountCode);
        //to make this more real and entreteinment lets asume
        // that the id field of response will be discount value
        int discountValue = response.id();

        if(!isValidDiscountCode)
            throw new APIException(HttpStatus.CONFLICT, "Invalid discount code");

        //calculate the value of booking
        double value = getTheBookingValue(house, dias);
        //calculate final booking value(after discount)

        double finalValue = value - discountValue;
        //set the user and house object into DTO Booking Obj

        BookingDto bookingDto1 = new BookingDto(null,
                                                user.getId(),
                                                house.getId(),
                                                bookingDto.startDate(),
                                                bookingDto.endDate(),
                                                bookingDto.discountCode(),
                                                value,
                                                finalValue,
                                                BookingStatus.SUCCESS);


        //Booking booking = BookingMapper.mapToBooking(bookingDto1);
        Booking booking = new Booking(null,
                                      bookingDto1.discountCode(),
                                      bookingDto1.startDate(),
                                      bookingDto1.endDate(),
                                      bookingDto1.value(),
                                      bookingDto1.finalValue(),
                                      bookingDto1.status(),
                                      user,
                                      house);
        Booking booking1 = bookingRepository.save(booking);

        return BookingMapper.mapToBookingDto(booking1);
    }

    private double getTheBookingValue(House house, long dias) {
        return house.getPrice() * dias;
    }

    private VerifyDiscountServiceResponse verifyDiscountCode(VerifyDiscountServiceRequest request) {

        return webClient.post()
                        .uri(EXTERNAL_SERVICE_URL)
                        .body(Mono.just(request), VerifyDiscountServiceRequest.class)
                        .retrieve()
                        .bodyToMono(VerifyDiscountServiceResponse.class)
                        .doOnSuccess(response -> {
                            // Handle successful response
                            LOGGER.debug("Success : {}", response);
                        })
                           .doOnError(error -> {
                            // Handle error response
                            LOGGER.error("ERROR : {}", error.getMessage());
                           })
                        .block();
    }

}
