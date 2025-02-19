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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean status;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneEntity> phones;


}
