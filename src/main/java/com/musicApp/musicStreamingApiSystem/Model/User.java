package com.musicApp.musicStreamingApiSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "user id should not be empty")
    private Integer userId;
    @NotEmpty(message = "username should not be empty")
    private Integer userName;
    @NotBlank(message = "userEmail should not be blank")
    private String userEmail;
    @NotBlank(message = "user password should not be blank")
    private String userPassword;
    @Size(min=10,max =12)
    @Pattern(regexp = "^[0-9]+$")
    private String userMobileNumber;
}
