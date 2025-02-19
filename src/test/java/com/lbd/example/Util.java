package com.lbd.example;

import com.lbd.example.domain.PhoneDTO;
import com.lbd.example.domain.UserDTO;
import com.lbd.example.entity.PhoneEntity;
import com.lbd.example.entity.UserEntity;
import com.lbd.example.util.Constant;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Util {
    public static final String NAME = "name";
    public static final String MAIL = "name@name.com";
    public static final String X_2_Y_3_OOOOT = "X2y3oooot";
    public static final String TOKEN = "token";
    public static final String BEARER = "bearer";
    public static final int CITY_CODE = 1;
    public static final String CU = "CU";


    public static UserDTO createUser() {

        return UserDTO.builder()
                .name(NAME)
                .email(MAIL)
                .isActive(true)
                .password(X_2_Y_3_OOOOT)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(TOKEN)
                .build();
    }

    public static UserEntity createUserEntity() {

        return UserEntity.builder()
                .name(NAME)
                .email(MAIL)
                .status(true)
                .password(X_2_Y_3_OOOOT)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(TOKEN)
                .build();
    }

    public static List<PhoneEntity> createPhoneEntityList() {
        return Collections.singletonList(PhoneEntity.builder()
                .cityCode(CITY_CODE)
                .countryCode(CU)
                .build());
    }

    public static List<PhoneDTO> createPhoneList() {
        return Collections.singletonList(PhoneDTO.builder()
                .cityCode(CITY_CODE)
                .countryCode(CU)
                .build());
    }

    public static HashMap<String, String> createHeader() {
        HashMap<String, String> h = new HashMap<>();
        h.put(Constant.AUTHORIZATION, BEARER + " " + TOKEN);
        return h;
    }

    public static HashMap<String, String> createNoHeader() {
        HashMap<String, String> h = new HashMap<>();

        return h;
    }
}
