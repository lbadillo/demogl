package com.lbd.example.util;

import com.lbd.example.domain.PhoneDTO;
import com.lbd.example.entity.PhoneEntity;
import com.lbd.example.entity.UserEntity;

public class PhoneTranslate {
    public static PhoneEntity toEntity(final PhoneDTO dto,
                                       final UserEntity user) {
        return PhoneEntity.builder()
                .user(user)
                .number(dto.getNumber())
                .cityCode(dto.getCityCode())
                .countryCode(dto.getCountryCode())
                .build();

    }

    public static PhoneDTO toDTO(final PhoneEntity entity) {
        return PhoneDTO.builder()

                .number(entity.getNumber())
                .cityCode(entity.getCityCode())
                .countryCode(entity.getCountryCode())
                .build();

    }
}
