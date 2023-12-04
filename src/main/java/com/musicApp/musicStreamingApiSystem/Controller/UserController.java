package com.musicApp.musicStreamingApiSystem.Controller;

import com.musicApp.musicStreamingApiSystem.DTO.AuthInputDto;
import com.musicApp.musicStreamingApiSystem.DTO.UserSignInInput;
import com.musicApp.musicStreamingApiSystem.Model.PlayList;
import com.musicApp.musicStreamingApiSystem.Model.User;
import com.musicApp.musicStreamingApiSystem.Service.AuthenticationTokenService;
import com.musicApp.musicStreamingApiSystem.Service.PlayListService;
import com.musicApp.musicStreamingApiSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    PlayListService playListService;

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @PostMapping("/signUp/user")
    public String UserSignUp(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.UserSignUp(user);
    }
    @PostMapping("/signIn/user")
    public String userSignIn(@RequestBody UserSignInInput userSignInInput) throws NoSuchAlgorithmException {
        return userService.userSignIn(userSignInInput);
    }
    @PostMapping("/SignOut/user")
    public String userSignOut(@RequestBody AuthInputDto authInputDto){
        return userService.userSignOut(authInputDto);
    }

    @PostMapping("/playlist")
    public String createPlayList(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestBody PlayList playList){
        return PlayListService.createPlayList(userEmail, tokenValue, playList);
    }

    //get playlist
    @GetMapping("/playlists")
    public List<PlayList> getPlayLists(@RequestParam String userEmail, @RequestParam String tokenValue){
        return playListService.getPlayLists(userEmail, tokenValue);
    }

    //delete playlist
    @DeleteMapping("/playlist/{id}")
    public String deletePlayList(@RequestParam String userEmail, @RequestParam String tokenValue, @PathVariable Integer id){
        return playListService.deletePlayList(userEmail, tokenValue, id);
    }

    //add song into playlist
    @PutMapping("/playlist/add/{playlistId}")
    public String addSong(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestParam Integer songId, @PathVariable Integer playlistId){
        return playListService.addSong(userEmail, tokenValue, songId, playlistId);
    }

    //remove song from playlist
    @DeleteMapping("/playlist/remove/{playlistId}")
    public String removeSong(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestParam Integer songId, @PathVariable Integer playlistId){
        return playListService.removeSong(userEmail, tokenValue, songId, playlistId);
    }
}
