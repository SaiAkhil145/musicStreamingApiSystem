package com.musicApp.musicStreamingApiSystem.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInInput {
    @Email
    private String userEmail;
    private String usePassword;
}
