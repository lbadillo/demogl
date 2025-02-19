package com.lbd.example.repository;

import com.lbd.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, String>{
     UserEntity findByEmail(String email);
     UserEntity findByToken(String token);
}

