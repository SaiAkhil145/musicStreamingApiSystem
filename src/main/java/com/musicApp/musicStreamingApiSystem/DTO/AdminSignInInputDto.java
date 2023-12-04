package com.musicApp.musicStreamingApiSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSignInInputDto {
    @Email
    @Pattern(regexp = "^.+@admin\\.com$")
    private String  adminEmail;
    private String password;
}
