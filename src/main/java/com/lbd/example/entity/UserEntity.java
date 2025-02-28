package com.lbd.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Ludwing Badillo
 * User entoty to parse users table on DB.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    /**
     * User ID: Generated UUID
     */
    @Id
    private String id;
    /**
     * user name.
     */
    private String name;
    /**
     * user email
     */
    private String email;
    /**
     * user password
     */
    private String password;
    /**
     * user status
     */
    private Boolean status;
    /**
     * user creation date
     */
    private LocalDateTime created;
    /**
     * user last login into the system
     */
    private LocalDateTime lastLogin;
    /**
     * user last token
     */
    private String token;
    /**
     * user phone list.
     * relation with phone table
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneEntity> phones;


}
