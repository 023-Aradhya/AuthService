package com.example.AuthService.repository;

import com.example.AuthService.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,Long> {
    public UserInfo findByUserName(String name);
}
