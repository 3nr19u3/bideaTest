package com.bideafactory.booking.controller;

import com.bideafactory.booking.dto.HouseDto;
import com.bideafactory.booking.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    public ResponseEntity<HouseDto> createHouse(@RequestBody HouseDto houseDto) {
        HouseDto houseDto1 = houseService.registerHouse(houseDto);
        return new ResponseEntity<>(houseDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<HouseDto> getHouseById(@PathVariable("id") UUID houseId) {
        HouseDto houseDto = houseService.getHouseById(houseId);
        return ResponseEntity.ok(houseDto);
    }

    @GetMapping
    public ResponseEntity<List<HouseDto>> getAllHouses() {
        List<HouseDto> houses = houseService.getAllHouses();
        return ResponseEntity.ok(houses);
    }

    @PutMapping("{id}")
    public ResponseEntity<HouseDto> updateHouse(@PathVariable("id") UUID houseId, @RequestBody HouseDto houseDto) {
        HouseDto houseDto1 = houseService.updateHouse(houseId, houseDto);
        return ResponseEntity.ok(houseDto1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID houseId) {
        houseService.deleteHouse(houseId);
        return ResponseEntity.ok("House deleted successfully");
    }
}
