package com.bideafactory.booking.repository;

import com.bideafactory.booking.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {
}
