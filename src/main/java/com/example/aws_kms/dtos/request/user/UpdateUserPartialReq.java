package com.example.aws_kms.dtos.request.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPartialReq {
    
    @NotNull(message = "id is required.")
    private Integer id;

    @Length(min = 3, max = 14)
    private String firstName;


    @Length(min = 3, max = 14)
    private String lastName;


    @Length(min = 3, max = 14)
    private String nationality;
    


    // public Integer getId() {
    //     return id;
    // }


    // public void setId(Integer id) {
    //     this.id = id;
    // }


    // public String getFirstName() {
    //     return firstName.trim();
    // }


    // public void setFirstName(String firstName) {
    //     this.firstName = firstName.trim();
    // }


    // public String getLastName() {
    //     return lastName.trim();
    // }


    // public void setLastName(String lastName) {
    //     this.lastName = lastName.trim();
    // }


    // public String getNationality() {
    //     return nationality.trim();
    // }


    // public void setNationality(String nationality) {
    //     this.nationality = nationality.trim();
    // }

}
