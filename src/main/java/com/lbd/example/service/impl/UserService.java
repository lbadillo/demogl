package com.lbd.example.service.impl;

import com.lbd.example.config.ConfigProperties;
import com.lbd.example.domain.UserDTO;
import com.lbd.example.entity.UserEntity;
import com.lbd.example.error.CryptoException;
import com.lbd.example.error.UserException;
import com.lbd.example.repository.UserRepository;
import com.lbd.example.service.IUserService;
import com.lbd.example.util.AESUtil;
import com.lbd.example.util.Constant;
import com.lbd.example.util.PhoneTranslate;
import com.lbd.example.util.UserTranslate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * Author: Ludwing Badillo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    /**
     * Repository injected
     */
    private final UserRepository userRep;
    /**
     * properties injected
     */
    private final ConfigProperties properties;


    /**
     * Implementation of interface
     * @param userDTO
     * @return new user
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (Objects.isNull(userRep.findByEmail(userDTO.getEmail()))) {
            userDTO.setIsActive(true);
            userDTO.setCreated(LocalDateTime.now());
            userDTO.setLastLogin(LocalDateTime.now());
            userDTO.setId(createUUID());
            userDTO.setToken(createJws(userDTO.getEmail()));
            userDTO.setPassword(cryptoFunction(userDTO.getPassword()));
            UserEntity user = UserTranslate.toEntity(userDTO);
            if (Objects.nonNull(userDTO.getPhones())) {
                user.setPhones(userDTO.getPhones().stream()
                        .map(ph -> PhoneTranslate.toEntity(ph, user))
                        .collect(Collectors.toList()));
            }
            UserEntity response = userRep.save(user);
            return UserTranslate.toDTONoPhones(response);
        } else {
            throw new UserException("User already exists");
        }


    }

    /**
     * Implementation of interface
     * @param token
     * @return user by token
     */
    @Override
    public UserDTO getUser(String token) {
        UserEntity response = userRep.findByToken(token);
        if (Objects.nonNull(response)) {
            response.setLastLogin(LocalDateTime.now());

            response.setToken(createJws(response.getEmail()));
            response = userRep.save(response);

            return UserTranslate.toDTO(response);
        } else {
            throw new UserException("User not found");
        }
    }

    /**
     * Private function to encrypt a text
     * @param text
     * @return crypto text
     */

    private String cryptoFunction(String text) {
        try {

            String password = properties.getPassword();
            String salt = properties.getSalt();
            IvParameterSpec ivParameterSpec = AESUtil.generateIv();
            SecretKey key = AESUtil.getKeyFromPassword(password, salt);


            return AESUtil.encryptPasswordBased(text, key, ivParameterSpec);


        } catch (Exception ex) {
            throw new CryptoException("Cannot resolve encryption process");
        }
    }

    /**
     *
     * @return new ID for user (UUID)
     */
    private String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     *
     * @param email
     * @return createe a new token
     */
    private String createJws(String email) {
        long expirationTime = Constant.EXPIRATION_TIME;
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }


}
