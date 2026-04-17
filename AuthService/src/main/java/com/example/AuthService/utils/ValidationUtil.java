package com.example.AuthService.utils;

import com.example.AuthService.model.UserInfoDto;

public class ValidationUtil {
    public static void validateUserAttributes(UserInfoDto dto) {

        if (dto == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }

        if (dto.getUserName() == null || dto.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

//        if (!isValidEmail(dto.getEmail())) {
//            throw new IllegalArgumentException("Invalid email format");
//        }

        if (!isValidPassword(dto.getPassword())) {
            throw new IllegalArgumentException(
                    "Password must be 8+ chars with uppercase, lowercase, number and special character"
            );
        }
    }

//    private static boolean isValidEmail(String email) {
//        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        return email != null && email.matches(regex);
//    }

    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return password != null && password.matches(regex);
    }
}
