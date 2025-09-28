package com.lekham.controller;

import com.lekham.api.RestApiResponse;
import com.lekham.communication.EmailService;
import com.lekham.dto.request.LoginRequest;
import com.lekham.dto.request.OtpRequest;
import com.lekham.dto.response.LoginResponse;
import com.lekham.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome(){

        return new ResponseEntity("This is  the Welcome Page",HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<RestApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) throws IllegalAccessException {

        return authService.loginOtp(request);
    }

    @PostMapping("/login-otp")
    public ResponseEntity<RestApiResponse<LoginResponse>> send(@RequestBody OtpRequest otpRequest) throws IllegalAccessException {
    return authService.verifyOtp(otpRequest);
    }

}
