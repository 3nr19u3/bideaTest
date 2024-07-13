package com.bideafactory.booking.mapper;

import com.bideafactory.booking.dto.HouseDto;
import com.bideafactory.booking.dto.UserDto;
import com.bideafactory.booking.entity.House;
import com.bideafactory.booking.entity.User;

public class HouseMapper {

    public static HouseDto mapToHouseDto(House house) {
        return new HouseDto(
                house.getId(),
                house.getName(),
                house.getPrice(),
                house.getStatus()
        );
    }

    public static House mapToHouse(HouseDto houseDto) {
        return new House(
                houseDto.uuid(),
                houseDto.name(),
                houseDto.price(),
                houseDto.status(),
                null
        );
    }
}
