package com.example.aws_kms.dtos.response;


import com.example.aws_kms.dtos.entity.user.UserEntityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailsResponse {
    
    private UserEntityDto user;

}
