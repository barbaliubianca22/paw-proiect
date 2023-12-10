package com.example.authservice;

import com.example.authservice.models.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AuthUser, String> { }
