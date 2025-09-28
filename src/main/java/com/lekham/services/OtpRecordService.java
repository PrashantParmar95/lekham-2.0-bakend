package com.lekham.services;

import com.lekham.dto.request.OtpRequest;
import com.lekham.entities.OtpRecord;
import com.lekham.repositories.OtpRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpRecordService {

    @Autowired
    private OtpRecordRepository otpRecordRepository;

    public OtpRecord save(OtpRecord otpRecord) {
        return otpRecordRepository.save(otpRecord);
    }

    public boolean matchOtpRequest(OtpRequest otpRequest) {
        String otp = fetchLatestOtp(otpRequest.email());

        return otp.equals(otpRequest.otp());
    }

    public String fetchLatestOtp(String email){
        String otp = otpRecordRepository.fetchLatestOTP(email);
        return otp;
    }
}
