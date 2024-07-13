package com.bideafactory.booking.service.impl;

import com.bideafactory.booking.dto.HouseDto;
import com.bideafactory.booking.entity.House;
import com.bideafactory.booking.exception.ResourceNotFoundException;
import com.bideafactory.booking.mapper.HouseMapper;
import com.bideafactory.booking.repository.HouseRepository;
import com.bideafactory.booking.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HouseServiceIpml implements HouseService {

    private HouseRepository houseRepository;

    @Override
    public HouseDto registerHouse(HouseDto houseDto) {
        House house = HouseMapper.mapToHouse(houseDto);
        House house1 = houseRepository.save(house);
        return HouseMapper.mapToHouseDto(house1);
    }

    @Override
    public HouseDto getHouseById(UUID houseId) {
        House house = houseRepository.findById(houseId)
                                     .orElseThrow(() ->
                                             new ResourceNotFoundException("House", "id", houseId));
        return HouseMapper.mapToHouseDto(house);
    }

    @Override
    public List<HouseDto> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream().map(HouseMapper::mapToHouseDto).collect(Collectors.toList());
    }

    @Override
    public HouseDto updateHouse(UUID houseId, HouseDto houseDto) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("House", "id", houseId));

        house.setName(houseDto.name());
        house.setPrice(houseDto.price());
        house.setStatus(houseDto.status());
        House updateHouse = houseRepository.save(house);

        return HouseMapper.mapToHouseDto(updateHouse);
    }

    @Override
    public void deleteHouse(UUID houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("House", "id", houseId));

        houseRepository.deleteById(houseId);

    }
}
