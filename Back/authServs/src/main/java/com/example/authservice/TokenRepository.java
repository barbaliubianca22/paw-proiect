package com.example.authservice;

import com.example.authservice.models.AuthUser;
import com.example.authservice.models.TokenSession;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<TokenSession, String> {
}
