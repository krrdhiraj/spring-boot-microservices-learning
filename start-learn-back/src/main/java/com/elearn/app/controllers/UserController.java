package com.elearn.app.controllers;

import com.elearn.app.dtos.UserDto;
import com.elearn.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    // insert user
    @PostMapping
    public ResponseEntity<UserDto> insertUser(@RequestBody UserDto userDto){
        UserDto dto = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();

    }
    @GetMapping("/{userId}")
    public UserDto getSingleUser(@PathVariable String userId){
        return userService.getSingleUser(userId);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }

}
