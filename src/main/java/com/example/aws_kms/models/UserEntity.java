package com.example.aws_kms.models;


import java.util.ArrayList;
import java.util.List;


import com.example.aws_kms.converters.StringTrimConverter;
import com.example.aws_kms.dtos.entity.user.UserEntityDto;
import com.example.aws_kms.dtos.request.user.CreateUserReq;
import com.example.aws_kms.enums.UserEntityStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name="users")
@Table(name="users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = StringTrimConverter.class)
    @Column(name="firstName",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String firstName;

    @Column(name="lastName",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String lastName;
    
    @Column(name="email",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String email;
    
    @Column(name="password",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String password;
    
    @Column(name="phone",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String phone;
    
    @Column(name="nationality",columnDefinition = "VARCHAR(255)",nullable = false,updatable = true)
    private String nationality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_profile")
    private UserEntityProfile userProfile;
    

    @Column(name="status",nullable = false,updatable = true)
    @Enumerated(EnumType.STRING)
    private UserEntityStatusEnum status=UserEntityStatusEnum.PENDING_APPROVAL;
    

    public static  UserEntity dtoToModel(CreateUserReq createUserRequest){
        UserEntity userEntity=new UserEntity();
        userEntity.setFirstName(createUserRequest.getFirstName());
        userEntity.setLastName(createUserRequest.getLastName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setPhone(createUserRequest.getPhone());
        userEntity.setNationality(createUserRequest.getNationality());
        return userEntity;
    }

    public  static  UserEntityDto modelToDto(UserEntity userEntity){
        UserEntityDto userEntityDto=new UserEntityDto();
        userEntityDto.setId(userEntity.getId());
        userEntityDto.setFirstName(userEntity.getFirstName());
        userEntityDto.setLastName(userEntity.getLastName());
        userEntityDto.setEmail(userEntity.getEmail());
        userEntityDto.setPhone(userEntity.getPhone());
        userEntityDto.setNationality(userEntity.getNationality());
        userEntityDto.setStatus(userEntity.getStatus());

        return userEntityDto;
    }

    public static List<UserEntityDto> modelToDtoList( List<UserEntity> userEntityList){
        List<UserEntityDto> userEntityDtoList=new ArrayList<UserEntityDto>();
        for(UserEntity userEntity: userEntityList){
            userEntityDtoList.add(UserEntity.modelToDto(userEntity));
        }
        return userEntityDtoList;
       
    }

    




}
