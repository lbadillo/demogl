package com.lbd.example.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String id;

    private String name;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Pattern(message = "The password field must be: a single capital letter, two non-consecutive numbers, maximum length of 12 and minimum of 8",
            regexp = "^(?!.*[A-Z].*[A-Z])(?=.*[A-Z])(?=(?:\\D*\\d\\D*){2})(?!.*\\d{2})[A-Za-z\\d]{8,12}$")
    private String password;
    private LocalDateTime lastLogin;
    private String token;
    private LocalDateTime created;
    private Boolean isActive;
    private List<PhoneDTO> phones;

}
