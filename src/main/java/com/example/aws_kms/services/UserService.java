package com.example.aws_kms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.aws_kms.dtos.entity.user.UserEntityDto;
import com.example.aws_kms.dtos.request.user.CreateUserReq;
import com.example.aws_kms.dtos.request.user.UpdateUserPartialReq;
import com.example.aws_kms.dtos.request.user.UpdateUserReq;
import com.example.aws_kms.exceptions.DuplicateEntityException;
import com.example.aws_kms.exceptions.ResourceNotFoundException;
import com.example.aws_kms.models.UserEntity;
import com.example.aws_kms.repositories.UserEntityRepository;



@Service
public class UserService {

    private final UserEntityRepository userEntityRepository;

    UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public Object getUser() {
        return null;
    }

    public List<UserEntityDto> getAllUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        return UserEntity.modelToDtoList(users);

    }

    public UserEntityDto getUserById(Integer id) {
        UserEntity user = userEntityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return UserEntity.modelToDto(user);

    }

    public UserEntityDto createUser(CreateUserReq createUserRequest) throws Exception {
        Optional<UserEntity> duplicateUser = userEntityRepository.findByEmail(createUserRequest.getEmail());
        if (duplicateUser.isPresent() == true) {
            throw new DuplicateEntityException("User", "email");
        }
        UserEntity newUser = UserEntity.dtoToModel(createUserRequest);
       return  UserEntity.modelToDto(userEntityRepository.save(newUser));

    }

    public Integer deleteUserById(Integer id) {
        UserEntity user = userEntityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        userEntityRepository.delete(user);
        return user.getId();

    }

    @Transactional(readOnly = false)
    public UserEntityDto updateUser(UpdateUserReq updateUserDetailsReq) {
        UserEntity user = userEntityRepository.findById(updateUserDetailsReq.getId()) .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        userEntityRepository.updateUserPartial(updateUserDetailsReq.getFirstName(), updateUserDetailsReq.getLastName(), user.getNationality(), user.getId());
        UserEntity updatedUser = userEntityRepository.findById(updateUserDetailsReq.getId()) .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return UserEntity.modelToDto(updatedUser);
    }

    public UserEntityDto updateUserPartial(UpdateUserPartialReq updateUserPartialReq) {
       

        UserEntity user = userEntityRepository.findById(updateUserPartialReq.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        if (updateUserPartialReq.getFirstName() != null) {
            user.setFirstName(updateUserPartialReq.getFirstName());
        }
        if (updateUserPartialReq.getLastName() != null) {
            user.setLastName(updateUserPartialReq.getLastName());
        }
        if (updateUserPartialReq.getNationality() != null) {
            user.setNationality(updateUserPartialReq.getNationality());
        }
        return UserEntity.modelToDto(userEntityRepository.save(user));

    }

}
