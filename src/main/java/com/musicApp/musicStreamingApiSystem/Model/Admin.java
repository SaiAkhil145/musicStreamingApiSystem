package com.musicApp.musicStreamingApiSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @NotBlank(message = "admin name should not be empty")
    private String adminName;
    @Pattern(regexp = "^.+@admin\\.com$")
    private String adminEmail;
    @NotBlank(message = "admin password should not be blank")
    private String adminPassword;
}
