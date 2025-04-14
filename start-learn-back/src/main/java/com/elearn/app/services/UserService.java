package com.elearn.app.services;

import com.elearn.app.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    UserDto getSingleUser(String userId);

    List<UserDto> getUsers();


}
