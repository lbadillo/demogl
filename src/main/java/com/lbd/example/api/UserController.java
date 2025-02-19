package com.lbd.example.api;

import com.lbd.example.domain.UserDTO;
import com.lbd.example.error.UserException;
import com.lbd.example.service.UserService;
import com.lbd.example.util.Constant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Objects;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@Slf4j
public class UserController {
    private UserService userService;

    @PostMapping(value = "/sign-up")
    public UserDTO addUser(@Valid @RequestBody final UserDTO user
    ) {

        return userService.createUser(user);

    }

    @GetMapping(value = "/login")
    public UserDTO getUser(@RequestHeader HashMap<String, String> headers) {
        String token = headers.get(Constant.AUTHORIZATION);
        if (Objects.nonNull(token)) {
            return userService.getUser(token.split(" ")[1]);
        } else {
            throw new UserException("User not found");
        }


    }
}
