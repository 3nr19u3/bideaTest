package com.bideafactory.booking.controller;

import com.bideafactory.booking.dto.UserDto;
import com.bideafactory.booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.registerUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") UUID userId, @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(userDto1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

}
