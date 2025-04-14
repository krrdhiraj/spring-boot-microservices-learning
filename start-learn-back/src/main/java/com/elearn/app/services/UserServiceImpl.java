package com.elearn.app.services;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.UserDto;
import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.RoleRepo;
import com.elearn.app.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepo roleRepo){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getUserName());
        user.setCreatedAt(new Date());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        user.setActive(true);
        user.setSmsVerified(false);
        user.setEmailVerified(false);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePath(AppConstants.DEFAULT_PROFILE_PATH);

        Role role = roleRepo.findByRoleName(AppConstants.ROLE_GUEST).orElseThrow(()-> new ResourceNotFoundException("No role found"));
        user.assignRole(role);
        User savedUser = userRepo.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new BadCredentialsException("User not found in the DataBase"));
        modelMapper.map(userDto, user);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new BadCredentialsException("User not found in the DataBase"));
        userRepo.delete(user);
    }

    @Override
    public UserDto getSingleUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new BadCredentialsException("User not found in the DataBase"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

}
