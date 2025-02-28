package com.lbd.example.service;

import com.lbd.example.domain.UserDTO;

/**
 * Author: Ludwing Badillo
 */

public interface IUserService {
    /**
     *
     * @param userDTO
     * @return new user userDTO
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     *
     * @param token
     * @return user by token
     */
    UserDTO getUser(String token);
}
