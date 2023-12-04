package com.musicApp.musicStreamingApiSystem.Service;

import com.musicApp.musicStreamingApiSystem.DTO.AuthInputDto;
import com.musicApp.musicStreamingApiSystem.Model.AuthenticationToken;
import com.musicApp.musicStreamingApiSystem.Repository.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    static IAuthenticationTokenRepo authRepo;
    public static void createToken(AuthenticationToken token) {
        authRepo.save(token);
    }

    public static boolean AdminAuthenticate(String adminEmail, String tokenValue) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token!=null){
            return token.getAdmin().getAdminEmail().equals(adminEmail);
        }
        return false;
    }

    public static boolean UserAuthenticate(AuthInputDto authInputDto) {
        AuthenticationToken token  = authRepo.findFirstByTokenValue(authInputDto.getTokenValue());
        if(token!=null){
            return token.getUser().getUserEmail().equals(authInputDto.getEmail());
        }
        return false;
    }

    public static boolean userAuthenticate(String userEmail, String tokenValue) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        if(token!=null){
            return token.getUser().getUserEmail().equals(userEmail);

        }else{
            return false;
        }
    }

    public void deleteToken(String tokenValue) {
        AuthenticationToken token = authRepo.findFirstByTokenValue(tokenValue);
        authRepo.delete(token);
    }
}
