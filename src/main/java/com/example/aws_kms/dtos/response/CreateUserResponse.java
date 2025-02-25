package com.example.aws_kms.dtos.response;

import com.example.aws_kms.dtos.entity.user.UserEntityDto;

import lombok.Data;


@Data
public class CreateUserResponse {
   private UserEntityDto user;

}
