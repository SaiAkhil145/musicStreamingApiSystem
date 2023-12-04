package com.musicApp.musicStreamingApiSystem.Service;

import com.musicApp.musicStreamingApiSystem.DTO.AuthInputDto;
import com.musicApp.musicStreamingApiSystem.DTO.UserSignInInput;
import com.musicApp.musicStreamingApiSystem.Model.AuthenticationToken;
import com.musicApp.musicStreamingApiSystem.Model.User;
import com.musicApp.musicStreamingApiSystem.Repository.IUserRepo;
import com.musicApp.musicStreamingApiSystem.Util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public String UserSignUp(User user) throws NoSuchAlgorithmException {
        String email = user.getUserEmail();
        User existingPatient=userRepo.findFirstByEmail(email);

        if(existingPatient!=null){
            return "email already in use please try signup another email";
        }
        String password = user.getUserPassword();
        String encryptPassword= PasswordEncryptor.encrypt(password);
        user.setUserPassword(encryptPassword);
        userRepo.save(user);
        return "user registered succesfully!!!!!!!!!!!";
    }


    public String userSignIn(UserSignInInput userSignInInput) throws NoSuchAlgorithmException {
        User user = userRepo.findFirstByUserEmail(userSignInInput.getUserEmail());
        if(user == null){
            return "invalid credentials";
        }
        String encryptedPassword = PasswordEncryptor.encrypt(userSignInInput.getUsePassword());
        if(encryptedPassword.equals(userSignInInput.getUsePassword())){
            AuthenticationToken token =  new AuthenticationToken(user);
            AuthenticationTokenService.createToken(token);
            return "token created successfully!!!!!!!";
        }
        return "invalid credentials";
    }


    public String userSignOut(AuthInputDto authInputDto) {
        if(AuthenticationTokenService.UserAuthenticate(authInputDto)){
            authenticationTokenService.deleteToken(authInputDto.getTokenValue());
            return "token deleted successfully";
        }
        return "unauthorized access";
    }
}
