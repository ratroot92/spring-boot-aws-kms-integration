package com.example.aws_kms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_details")
@Data
public class UserEntityProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="avatar",nullable = true,updatable = true)
    private String userAvatar;

    @Column(name="profile_image",nullable = true,updatable = true)
    private String userPorfileImage;

    @Column(name="address",nullable = true,updatable = true)
    private String address;

    @OneToOne(mappedBy = "userProfile")
    private UserEntity user;
    
}
