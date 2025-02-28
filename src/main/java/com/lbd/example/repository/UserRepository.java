package com.lbd.example.repository;

import com.lbd.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Ludwong Badillo
 */
public interface UserRepository  extends JpaRepository<UserEntity, String>{
     /**
      *
      * @param email
      * @return a user by email
      */
     UserEntity findByEmail(String email);

     /**
      *
      * @param token
      * @return a user by token
      */
     UserEntity findByToken(String token);
}

