package com.lbd.example.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Ludwing Badillo
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    /**
     * User id.
     */
    private String id;

    /**
     * User name.
     */
    private String name;

    /**
     * User email.
     *  Validation based on regex
     *  Managed by HandleExceptions
     */

    @NotNull(message= "Email must be present")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    /**
     * User Password.
     * validation based on regex
     * Managed by handleExceptions
     */

    @NotNull(message= "Password must be present")
    @Pattern(message = "The password field must be: a single capital letter, two non-consecutive numbers, maximum length of 12 and minimum of 8",
            regexp = "^(?!.*[A-Z].*[A-Z])(?=.*[A-Z])(?=(?:\\D*\\d\\D*){2})(?!.*\\d{2})[A-Za-z\\d]{8,12}$")
    private String password;

    /**
     * User Last Login
     */
    private LocalDateTime lastLogin;

    /**
     * User Token
     */
    private String token;

    /**
     * User creation Date
     * Internal data
     */
    private LocalDateTime created;

    /**
     * User status
     */
    private Boolean isActive;

    /**
     * User List of phones
     */
    private List<PhoneDTO> phones;

}
