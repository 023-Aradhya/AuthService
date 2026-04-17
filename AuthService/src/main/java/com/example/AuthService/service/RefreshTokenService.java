package com.example.AuthService.service;

import com.example.AuthService.entities.RefreshToken;
import com.example.AuthService.entities.UserInfo;
import com.example.AuthService.repository.RefreshTokenRepository;
import com.example.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {
    @Autowired RefreshTokenRepository  refreshTokenRepository;
    @Autowired UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        UserInfo userInfoExtracted = userRepository.findByUserName(username);
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userInfoExtracted)
                .token(UUID.randomUUID().toString())
                .expiresDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiresDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() +" REFRESH TOKEN IS EXPIRED. PLEASE MAKE A NEW LOGIN....");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
}
