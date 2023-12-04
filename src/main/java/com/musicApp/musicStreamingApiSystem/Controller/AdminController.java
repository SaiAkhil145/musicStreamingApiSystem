package com.musicApp.musicStreamingApiSystem.Controller;

import com.musicApp.musicStreamingApiSystem.DTO.AdminSignInInputDto;
import com.musicApp.musicStreamingApiSystem.Model.Admin;
import com.musicApp.musicStreamingApiSystem.Model.Song;
import com.musicApp.musicStreamingApiSystem.Service.AdminService;
import com.musicApp.musicStreamingApiSystem.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/signUp/admin")
    public String adminSignUp(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        return adminService.adminSignUp(admin);
    }

    //sign in
    @PostMapping("/signIn/Admin")
    public String signInAdmin(@RequestBody AdminSignInInputDto adminSignInInputDto) throws NoSuchAlgorithmException{
        return adminService.signInAdmin(adminSignInInputDto);
    }

    //sign out
    @PostMapping("/signOut/admin")
    public String signOutAdmin(@RequestParam String adminEmail,@RequestParam String tokenValue){
        return adminService.adminSignOut(adminEmail,tokenValue);
    }
    @PostMapping("/add/song")
    public String addSong(@RequestParam String adminEmail,@RequestParam String tokenValue,@RequestBody Song song){
        return adminService.addSong(adminEmail,tokenValue,song);
    }
    @GetMapping("/fetch/songs")
    public List<Song> getAllSongs(@RequestParam String adminEmail,@RequestParam String tokenValue){
        return adminService.getAllSongs(adminEmail,tokenValue);
    }
    @PutMapping("/update/Song")
    public String updateSong(@RequestParam String adminEmail,@RequestParam String tokenValue,@RequestBody Song song){
        return adminService.updateSong(adminEmail,tokenValue,song);
    }
    @DeleteMapping("/delete/song")
    public String deleteSong(@RequestParam String adminEmail,@RequestParam String tokenValue,@PathVariable Integer id){
        return adminService.deleteSong(adminEmail,tokenValue,id);
    }


}
