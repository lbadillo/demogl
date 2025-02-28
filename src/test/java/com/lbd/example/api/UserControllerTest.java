package com.lbd.example.api;

import com.lbd.example.domain.UserDTO;
import com.lbd.example.error.UserException;
import com.lbd.example.service.IUserService;
import com.lbd.example.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.lbd.example.Util.createHeader;
import static com.lbd.example.Util.createNoHeader;
import static com.lbd.example.Util.createPhoneList;
import static com.lbd.example.Util.createUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private IUserService service;

    @InjectMocks
    private UserController controller;

    @Test
    public void addUserTest() {
        when(service.createUser(any())).thenReturn(createUser());
        UserDTO user = controller.addUser(createUser());
        Assertions.assertNotNull(user);
    }

    @Test
    public void getUserTest() {
        UserDTO user = createUser();
        user.setPhones(createPhoneList());
        when(service.getUser(any())).thenReturn(user);
        UserDTO userR = controller.getUser(createHeader());
        Assertions.assertNotNull(userR);
    }

    @Test
    public void getUserNoOkTest() {

        assertThrows(UserException.class, () -> {
            controller.getUser(createNoHeader());
        });
    }


}
