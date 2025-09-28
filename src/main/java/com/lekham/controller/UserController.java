package com.lekham.controller;


import com.lekham.api.RestApiResponse;
import com.lekham.dto.UserDto;
import com.lekham.entities.Users;
import com.lekham.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("/add")
    public RestApiResponse<Users> addUser(@RequestBody Users user){
        return userService.addUser(user);
    }


    @GetMapping("/list")
    public RestApiResponse<List<UserDto>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/update")
    public RestApiResponse<Users> getAllUsers(@RequestBody Users user){
        return userService.updateUsers(user);
    }


}
