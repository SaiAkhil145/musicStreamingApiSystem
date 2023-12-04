package com.musicApp.musicStreamingApiSystem.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInputDto {
    @Email
    private String email;
    private String tokenValue;
}
