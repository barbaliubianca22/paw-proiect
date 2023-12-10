package com.example.authservice.services;

import com.example.authservice.TokenRepository;
import com.example.authservice.exceptions.ForbiddenException;
import com.example.authservice.exceptions.NotFoundException;
import com.example.authservice.exceptions.NotFoundException;
import com.example.authservice.models.AuthUser;
import com.example.authservice.UserRepository;
import com.example.authservice.models.TokenSession;
import com.fasterxml.jackson.databind.JsonNode;
import org.antlr.v4.runtime.Token;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/register")
    public String register(@RequestBody JsonNode userLoginData){
        String username = userLoginData.findValue("username").asText();
        String password = userLoginData.findValue("password").asText();

        String sha256password = DigestUtils.sha256Hex(password);

        if(username.isEmpty() || password.isEmpty()){
            throw new NotFoundException();
        }
        AuthUser authUser = new AuthUser(0L, username, sha256password, "normalUser");
        userRepository.save(authUser);
        return "Successfully Registered";
    }

    @PostMapping("/login")
    public TokenSession login(@RequestBody JsonNode userLoginData) {
        String username = userLoginData.findValue("username").asText();
        String password = userLoginData.findValue("password").asText();
        String sha256password = DigestUtils.sha256Hex(password);

        AuthUser authUser = new AuthUser(0L, username, sha256password, "normalUser");
        Optional<AuthUser> userData = userRepository.findById(username);
        if (userData.isEmpty()) {
            throw new ForbiddenException();
        }
        if (!userData.get().getHashed_pass().equals(sha256password)) {
            throw new ForbiddenException();
        }
        TokenSession tokenSession = new TokenSession(UUID.randomUUID().toString(),
                Instant.now().plus(7, ChronoUnit.DAYS ).toEpochMilli());
        tokenRepository.save(tokenSession);
        return tokenSession;
    }

    @PostMapping("/validate")
    public TokenSession validateToken(@RequestBody JsonNode token) {
        String tokenValue = token.findValue("token").asText();
        Optional<TokenSession> tokenSession = tokenRepository.findById(tokenValue);
        if (tokenSession.isEmpty() || tokenSession.get().getExpirationDate() < Instant.now().toEpochMilli()) {
            throw new ForbiddenException();
        }
        return tokenSession.get();
    }


    @GetMapping("/list")
    public List<AuthUser> getUser() {
        return (List<AuthUser>) userRepository.findAll();
    }
}