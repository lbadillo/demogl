package com.lbd.example.service;

import com.lbd.example.config.ConfigProperties;
import com.lbd.example.domain.UserDTO;
import com.lbd.example.entity.UserEntity;
import com.lbd.example.error.CryptoException;
import com.lbd.example.error.UserException;
import com.lbd.example.repository.UserRepository;
import com.lbd.example.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.lbd.example.Util.TOKEN;
import static com.lbd.example.Util.createPhoneList;
import static com.lbd.example.Util.createUser;
import static com.lbd.example.Util.createUserEntity;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    public static final String NUMBER = "1234";
    public static final String PASSWORD = "password";
    @Mock
    private UserRepository repository;

    @Mock
    private ConfigProperties properties;

    @InjectMocks
    private UserService service;


    @Test
    public void creteUserTest() {
        UserEntity user = createUserEntity();
        when(repository.findByEmail(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(user);
        when(properties.getSalt()).thenReturn(NUMBER);
        when(properties.getPassword()).thenReturn(PASSWORD);
        UserDTO userDTO = service.createUser(createUser());
        Assertions.assertNotNull(userDTO.getEmail());
    }

    @Test
    public void creteUserPhonesTest() {
        UserDTO userDTO = createUser();
        userDTO.setPhones(createPhoneList());
        UserEntity user = createUserEntity();
        when(repository.findByEmail(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(user);
        when(properties.getSalt()).thenReturn(NUMBER);
        when(properties.getPassword()).thenReturn(PASSWORD);
        UserDTO userDTOR = service.createUser(userDTO);
        Assertions.assertNotNull(userDTOR.getEmail());
    }

    @Test
    public void creteUserNoCryptoTest() {

        UserEntity user = createUserEntity();
        when(repository.findByEmail(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(user);
        assertThrows(CryptoException.class, () -> {
            service.createUser(createUser());
        });
    }

    @Test
    public void creteUserNoOkTest() {
        UserEntity user = createUserEntity();
        when(repository.findByEmail(any())).thenReturn(user);
        assertThrows(UserException.class, () -> {
            service.createUser(createUser());
        });
    }

    @Test
    public void getUserNoOkTest() {
        when(repository.findByToken(any())).thenReturn(null);
        assertThrows(UserException.class, () -> {
            service.getUser(TOKEN);
        });
    }

    @Test
    public void getUserTest() {
        UserEntity user = createUserEntity();
        when(repository.findByToken(any())).thenReturn(user);
        when(repository.save(any())).thenReturn(user);
        UserDTO userDTOR = service.getUser(TOKEN);
        Assertions.assertNotNull(userDTOR.getEmail());
    }



}
