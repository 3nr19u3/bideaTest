package com.bideafactory.booking.service;

import com.bideafactory.booking.dto.HouseDto;

import java.util.List;
import java.util.UUID;

public interface HouseService {

    HouseDto registerHouse(HouseDto houseDto);

    HouseDto getHouseById(UUID houseId);

    List<HouseDto> getAllHouses();

    HouseDto updateHouse(UUID houseId, HouseDto houseDto);

    void deleteHouse(UUID houseId);
}
