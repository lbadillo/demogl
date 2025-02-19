package com.lbd.example.util;

import com.lbd.example.domain.UserDTO;
import com.lbd.example.entity.UserEntity;

import java.util.Objects;
import java.util.stream.Collectors;

public class UserTranslate {
    public static UserEntity toEntity(final UserDTO dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .lastLogin(dto.getLastLogin())
                .created(dto.getCreated())
                .token(dto.getToken())
                .status(dto.getIsActive())
                .build();
    }

    public static UserDTO toDTONoPhones(final UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .lastLogin(entity.getLastLogin())
                .created(entity.getCreated())
                .isActive(entity.getStatus())
                .token(entity.getToken())
                .build();

    }

    public static UserDTO toDTO(final UserEntity entity) {
        UserDTO tmp = toDTONoPhones(entity);
        if (Objects.nonNull(entity.getPhones())) {
            tmp.setPhones(entity.getPhones().stream()
                    .map(PhoneTranslate::toDTO)
                    .collect(Collectors.toList()));
        }
        return tmp;
    }


}
