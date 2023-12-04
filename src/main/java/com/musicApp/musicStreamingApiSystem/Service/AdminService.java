package com.musicApp.musicStreamingApiSystem.Service;

import com.musicApp.musicStreamingApiSystem.DTO.AdminSignInInputDto;
import com.musicApp.musicStreamingApiSystem.Model.Admin;
import com.musicApp.musicStreamingApiSystem.Model.AuthenticationToken;
import com.musicApp.musicStreamingApiSystem.Model.Song;
import com.musicApp.musicStreamingApiSystem.Repository.IAdminRepo;
import com.musicApp.musicStreamingApiSystem.Util.PasswordEncryptor;
import com.musicApp.musicStreamingApiSystem.Util.ValidateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import com.musicApp.musicStreamingApiSystem.Controller.AdminController;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    SongService songService;


    public String adminSignUp(Admin admin) throws NoSuchAlgorithmException {
        if(ValidateEmail.isValid(admin.getAdminEmail())){
            Admin existingAdmin=adminRepo.findFirstByAdminEmail(admin.getAdminEmail());
                if(existingAdmin!=null){
                    return "email already registered try with another email";
                }else{
                    String encryptPassword = PasswordEncryptor.encrypt(admin.getAdminPassword());
                    admin.setAdminPassword(encryptPassword);
                    adminRepo.save(admin);
                    return "admin registered successfully!!!!!!!!!!!!!!!!!!!!";
                }
            }

        return "invalid admin email or password";
    }

    public String signInAdmin(AdminSignInInputDto adminSignInInputDto) throws NoSuchAlgorithmException {
        if(ValidateEmail.isValid(adminSignInInputDto.getAdminEmail())){
            Admin existingAdmin=adminRepo.findFirstByAdminEmail(adminSignInInputDto.getAdminEmail());
            if(existingAdmin==null){
                return "please signUp first !!!!!!!!!!!!!!!!";
            }else{
                String password=adminSignInInputDto.getPassword();
                String encryptedPassword= PasswordEncryptor.encrypt(password);
                if(encryptedPassword.equals(existingAdmin.getAdminPassword())){
                    AuthenticationToken token = new AuthenticationToken(existingAdmin);
                    AuthenticationTokenService.createToken(token);

                    return "token created with value of"+token.getTokenValue();
                }
            }
        }
        return "invalid email or password";
    }


    public String adminSignOut(String adminEmail, String tokenValue) {
        if(AuthenticationTokenService.AdminAuthenticate(adminEmail,tokenValue)){
            authenticationTokenService.deleteToken(tokenValue);
            return "signOut successfull";
        }
        return "un authenticated access!!!!!!";
    }

    public String addSong(String adminEmail, String tokenValue, Song song) {
        if(AuthenticationTokenService.AdminAuthenticate(adminEmail,tokenValue)){
            songService.addSong(song);
            return "song added successfully";
        }else{
            return "you need to sign in to perform this operation or you dont have permission to perfomr this specific operation.";
        }
    }

    public List<Song> getAllSongs(String adminEmail, String tokenValue) {
        List<Song> songList = new ArrayList<>();
        if(AuthenticationTokenService.AdminAuthenticate(adminEmail,tokenValue)){
           songList=songService.getAllSongs();
        }
        return songList;
    }

    public String updateSong(String adminEmail, String tokenValue, Song song) {

        if(AuthenticationTokenService.AdminAuthenticate(adminEmail,tokenValue)){
            return songService.updateSong(song);
        }
        return "unauthorized access!!!!!!!";
    }

    public String deleteSong(String adminEmail, String tokenValue, Integer id) {
        if(AuthenticationTokenService.AdminAuthenticate(adminEmail,tokenValue)){
            return songService.deleteSong(id);
        }
        return "unauthorized access";
    }
}
