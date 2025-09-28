package com.lekham.services;


import com.lekham.api.RestApiResponse;
import com.lekham.communication.EmailService;
import com.lekham.dto.UserDto;
import com.lekham.dto.request.LoginRequest;
import com.lekham.dto.request.OtpRequest;
import com.lekham.dto.response.LoginResponse;
import com.lekham.entities.OtpRecord;
import com.lekham.entities.Users;
import com.lekham.enums.OtpType;
import com.lekham.jwt.JwtService;
import com.lekham.repositories.UserRepository;
import com.lekham.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpRecordService otpRecordService;

    public ResponseEntity<RestApiResponse<LoginResponse>> loginOtp(LoginRequest request) throws IllegalAccessException {
        Optional<Users> userOptional = userRepository.findByEmail(request.email());
        if (userOptional.isEmpty()) {
            return new ResponseEntity(RestApiResponse.badResponseBuilder("Invalid User"), HttpStatus.BAD_REQUEST);
        }
        String otp = null;
        if(request.otp_login()) {
            try {

                otp = emailService.sendOtp(request.email());
                OtpRecord otpRecord = new OtpRecord();
                otpRecord.setOtp(otp);
                otpRecord.setEmail(request.email());
                otpRecord.setOtpType(OtpType.LOGIN);
                otpRecord.setTime(new Date());
                otpRecordService.save(otpRecord);
                return new ResponseEntity(RestApiResponse.successResponseBuilder("OTP send successfully", null), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(RestApiResponse.badResponseBuilder("Not Able to send OTP, please retry"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {

            try {
                Users user = userOptional.get();
                String token = jwtService.generateToken(user);
                UserDto userDto = new UserDto(user.getUsername(), user.getEmail(), user.getUserBio() != null ? user.getUserBio() : null, user.getPhoneNumber());
                LoginResponse loginResponse = new LoginResponse(token, userDto);
                return new ResponseEntity<>(RestApiResponse.successResponseBuilder(messageUtil.get("user.login.successfully"), loginResponse), HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity(RestApiResponse.badResponseBuilder("Not Able to Login"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    }

    public ResponseEntity<RestApiResponse<LoginResponse>> verifyOtp(OtpRequest otpRequest) throws IllegalAccessException {

        Optional<Users> userOptional = userRepository.findByEmail(otpRequest.email());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(RestApiResponse.badResponseBuilder("Invalid User"),HttpStatus.UNAUTHORIZED);
        }

        boolean otpMatched = otpRecordService.matchOtpRequest(otpRequest);
        if(otpMatched){
            Users user = userOptional.get();
            String token = jwtService.generateToken(user);
            UserDto userDto = new UserDto(user.getUsername(), user.getEmail(), user.getUserBio() != null ? user.getUserBio() : null, user.getPhoneNumber());
            LoginResponse loginResponse = new LoginResponse(token, userDto);
            return  new ResponseEntity<>(RestApiResponse.successResponseBuilder(messageUtil.get("user.login.successfully"), loginResponse),HttpStatus.OK);
        }

        return  new ResponseEntity<>(RestApiResponse.badResponseBuilder("Invalid OTP"),HttpStatus.BAD_REQUEST);
    }

}
