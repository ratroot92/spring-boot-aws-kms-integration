package com.example.aws_kms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.aws_kms.models.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer>{
    
    Optional<UserEntity> findByEmail(String email);
    @Modifying
    @Query("UPDATE users u set u.firstName = ?1, u.lastName = ?2, u.nationality = ?3 where u.id = ?4")
    int updateUserPartial(String firstName, String lastName,String nationality,Integer id);
}
