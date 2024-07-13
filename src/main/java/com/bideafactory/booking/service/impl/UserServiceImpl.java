package com.bideafactory.booking.service.impl;

import com.bideafactory.booking.dto.UserDto;
import com.bideafactory.booking.entity.User;
import com.bideafactory.booking.exception.ResourceNotFoundException;
import com.bideafactory.booking.mapper.UserMapper;
import com.bideafactory.booking.repository.UserRepository;
import com.bideafactory.booking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        try{
            User user = UserMapper.mapToUse(userDto);
            User user1 = userRepository.save(user);
            return UserMapper.mapToUserDto(user1);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.name());
        user.setLastName(userDto.lastName());
        user.setAge(userDto.age());
        user.setPhoneNumber(userDto.phoneNumber());
        User updateUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));

        userRepository.deleteById(userId);
    }
}
