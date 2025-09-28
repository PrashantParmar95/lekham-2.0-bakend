package com.lekham.dto.request;


public record LoginRequest(
        String email, String password,boolean otp_login
) {
}
