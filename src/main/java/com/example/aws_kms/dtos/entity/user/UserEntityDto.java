package com.example.aws_kms.dtos.entity.user;

import com.example.aws_kms.enums.UserEntityStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nationality;
    private UserEntityStatusEnum status;
    
}
