package com.example.aws_kms.services;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.aws_kms.exceptions.ResourceNotFoundException;
import com.example.aws_kms.models.UserEntity;
import com.example.aws_kms.models.UserEntityProfile;
import com.example.aws_kms.repositories.UserEntityRepository;

@Service
public class UserProfileService {
    private final Path root = Paths.get("public/user/avatar/");
    private final UserEntityRepository userEntityRepository;
    private final int MAX_FILE_SIZE = 5 * 1000000;

    UserProfileService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    private String getFileExtension(MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public void createUserProfile(Integer id, MultipartFile file, String address) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is required.");
            }
            Integer fileSize = ((int) file.getSize());
            if (fileSize <= MAX_FILE_SIZE) {
                throw new RuntimeException("Invalid file size.");
            }
            UserEntity user = userEntityRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found."));
            String fileName = "avatar_" + System.currentTimeMillis() + "" + getFileExtension(file);
            Files.copy(file.getInputStream(), root.resolve(fileName));
            UserEntityProfile userProfile = new UserEntityProfile();
            userProfile.setUserAvatar(fileName);
            userProfile.setAddress(address);
            user.setUserProfile(userProfile);

            userEntityRepository.save(user);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }

    }

}
