package com.example.aws_kms.dtos.response;

import java.util.List;

import com.example.aws_kms.dtos.entity.user.UserEntityDto;

import lombok.Data;


@Data
public class GetAllUsersResponse {
    private List<UserEntityDto> users;
}
