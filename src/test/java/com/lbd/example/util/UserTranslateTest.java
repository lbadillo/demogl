package com.lbd.example.util;

import com.lbd.example.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lbd.example.Util.createPhoneEntityList;
import static com.lbd.example.Util.createUserEntity;

public class UserTranslateTest {
    @Test
    public void testUserTraslate(){
        UserEntity user = createUserEntity();
        user.setPhones(createPhoneEntityList());
        Assertions.assertNotNull(UserTranslate.toDTO(user).getPhones());


    }
}
