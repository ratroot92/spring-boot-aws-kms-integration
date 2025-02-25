package com.example.aws_kms.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aws_kms.dtos.entity.user.UserEntityDto;
import com.example.aws_kms.dtos.request.user.CreateUserReq;
import com.example.aws_kms.dtos.request.user.UpdateUserPartialReq;
import com.example.aws_kms.dtos.request.user.UpdateUserReq;
import com.example.aws_kms.dtos.response.CreateUserResponse;
import com.example.aws_kms.dtos.response.DeleteUserByIdResponse;
import com.example.aws_kms.dtos.response.GetAllUsersResponse;
import com.example.aws_kms.dtos.response.GetUserByIdResponse;
import com.example.aws_kms.dtos.response.UpdateUserDetailsResponse;
import com.example.aws_kms.dtos.response.UpdateUserPartialRes;
import com.example.aws_kms.models.UserEntity;
import com.example.aws_kms.services.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();
        getAllUsersResponse.setUsers(userService.getAllUsers());
        return new ResponseEntity<GetAllUsersResponse>(getAllUsersResponse, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdResponse> getUserById(@PathVariable(name = "id", required = false) Integer id) {
        GetUserByIdResponse getUserByIdResponse = new GetUserByIdResponse();
        getUserByIdResponse.setUser(userService.getUserById(id));
        return new ResponseEntity<GetUserByIdResponse>(getUserByIdResponse, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserReq createUserRequest)
            throws Exception {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUser(userService.createUser(createUserRequest));
        return new ResponseEntity<CreateUserResponse>(createUserResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserByIdResponse> deleteUserById(
            @PathVariable(name = "id", required = true) Integer id) {
                DeleteUserByIdResponse deleteUserByIdResponse = new DeleteUserByIdResponse(userService.deleteUserById(id));
        return new ResponseEntity<DeleteUserByIdResponse>(deleteUserByIdResponse, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UpdateUserDetailsResponse > updateUser(@RequestBody @Valid UpdateUserReq updateUserDetailsRequest) {
        UserEntityDto user= userService.updateUser(updateUserDetailsRequest);
        UpdateUserDetailsResponse updateUserDetailsResponse=new UpdateUserDetailsResponse(user);
        return new ResponseEntity<UpdateUserDetailsResponse>(updateUserDetailsResponse,HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<UpdateUserPartialRes > updateUserPartial(@RequestBody @Valid UpdateUserPartialReq updateUserDetailsRequest) {
        UserEntityDto user= userService.updateUserPartial(updateUserDetailsRequest);
        UpdateUserPartialRes updateUserDetailsResponse=new UpdateUserPartialRes(user);
        return new ResponseEntity<UpdateUserPartialRes>(updateUserDetailsResponse,HttpStatus.OK);
    }

}
