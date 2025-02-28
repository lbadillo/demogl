package com.lbd.example.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbd.example.domain.UserDTO;
import com.lbd.example.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class WebControllerTest {
    public static final String MAIL = "ludwing@gmail.com";
    public static final String PASS = "X2y4zmiop";
    public static final String TEST = "TEST";
    public static final String EMPTY = "";
    public static final String MESSAGE_01 = "The password field must be: a single capital letter, two non-consecutive numbers, maximum length of 12 and minimum of 8";
    public static final String MESSAGE_02 = "Email must be present";
    public static final String MESSAGE_03 = "Email is not valid";
    public static final String MESSAGE_04 = "Password must be present";
    public static final int ERROR_01 = 1;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService service;

    @Test
    public void dataValidationOkTest() throws Exception {
        UserDTO userTmp = createDTO(MAIL, PASS);
        when(service.createUser(any())).thenReturn(userTmp);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(userTmp)
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void dataValidationNoOkEmailTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(createDTO(TEST, PASS))
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )

                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ERROR_01));


    }

    @Test
    public void dataValidationNoEmailTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(createDTO(null, PASS))
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )

                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ERROR_01))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(MESSAGE_02));


    }

    @Test
    public void dataValidationEmailEmptyTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(createDTO(EMPTY, PASS))
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )

                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ERROR_01))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(MESSAGE_03));


    }

    @Test
    public void dataValidationNoPassTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(createDTO(MAIL, null))
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )

                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ERROR_01))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(MESSAGE_04));


    }

    @Test
    public void dataValidationEmptyPassTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/sign-up")
                                .content(asJsonString(createDTO(MAIL, EMPTY))
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)

                )

                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ERROR_01))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(MESSAGE_01));


    }

    private UserDTO createDTO(String email, String password) {
        return UserDTO.builder()
                .email(email)
                .password(password)
                .build();

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
