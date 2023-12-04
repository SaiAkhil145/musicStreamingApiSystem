package com.musicApp.musicStreamingApiSystem.Repository;

import com.musicApp.musicStreamingApiSystem.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
    Admin findFirstByAdminEmail(String adminEmail);
}