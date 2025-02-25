package com.example.aws_kms.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.aws_kms.services.UserProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user/profile")

public class UserProfileController {

    private final UserProfileService userProfileService;
    UserProfileController(UserProfileService userProfileService){
        this.userProfileService=userProfileService;
    }
    
    @PostMapping("/{id}")
    public void createProfile(@PathVariable(name = "id",required = true) Integer id, @RequestParam("avatar") @Valid MultipartFile avatar,String address){
        userProfileService.createUserProfile(id,avatar,address);

    }
}
