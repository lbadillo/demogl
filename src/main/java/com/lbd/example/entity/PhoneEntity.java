package com.lbd.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="phone")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    @Column(name= "city_code")
    private int  cityCode;
    @Column(name= "country_code")
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;
}
