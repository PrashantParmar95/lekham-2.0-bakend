package com.lekham.dto;

public record UserDto(
        String username,
        String email,
        String userBio,
        String phoneNumber
) {}
