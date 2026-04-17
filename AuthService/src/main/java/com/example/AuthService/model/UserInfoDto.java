package com.example.AuthService.model;

import com.example.AuthService.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {

    private String userName;

    private String lastName;

    private Long phoneNumber;

    private String email;


}
