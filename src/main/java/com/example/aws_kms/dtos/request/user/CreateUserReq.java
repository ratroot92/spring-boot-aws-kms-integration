package com.example.aws_kms.dtos.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserReq {

    @NotNull(message = "firstName is required")
    private String firstName;

    @NotNull(message = "lastName is required.")
    private String lastName;

    @Email(message = "Should be valid email address.")
    private String email;

    @NotNull(message = "password is required.")
    private String password;

    @NotNull(message = "nationality is required.")
    private String nationality;

    @NotNull(message = "phone is required.")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid phoneNo provided.")
    private String phone;

}
