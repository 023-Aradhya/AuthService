package com.example.AuthService.service;

import com.example.AuthService.entities.UserInfo;
import com.example.AuthService.model.UserInfoDto;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserInfo user=userRepository.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("could not find user...");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userinfoDto){
        return userRepository.findByUserName(userinfoDto.getUserName());
    }

    public Boolean signUpUser(UserInfoDto userinfoDto){

        ValidationUtil.validateUserAttributes(userinfoDto);
        userinfoDto.setPassword(passwordEncoder.encode(userinfoDto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExist(userinfoDto))){
            return false;
        }
        String userId= UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId,userinfoDto.getUserName(),
                userinfoDto.getPassword(),new HashSet<>()));
        return true;
    }


}
