package com.example.aws_kms.dtos.request.UserProfile;

import jakarta.validation.constraints.NotBlank;

public class CreateUserProfileReq {
    @NotBlank
    private String avatar;
    @NotBlank
    private String address;
}
