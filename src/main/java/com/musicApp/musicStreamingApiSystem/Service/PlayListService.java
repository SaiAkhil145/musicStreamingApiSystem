package com.musicApp.musicStreamingApiSystem.Service;

import com.musicApp.musicStreamingApiSystem.Model.PlayList;
import com.musicApp.musicStreamingApiSystem.Model.Song;
import com.musicApp.musicStreamingApiSystem.Model.User;
import com.musicApp.musicStreamingApiSystem.Repository.IPlaylistRepo;
import com.musicApp.musicStreamingApiSystem.Repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListService {
    @Autowired
    ISongRepo songRepo;

    @Autowired
    static IPlaylistRepo playlistRepo;
    @Autowired
    static UserService userService;

    public static String createPlayList(String userEmail, String tokenValue, PlayList playList) {
        if(AuthenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            User user = userService.userRepo.findFirstByUserEmail(userEmail);
            playList.setUser(user);
            playlistRepo.save(playList);
            return "playlist created";
        }
        return "Unauthorised access";
    }

    public List<PlayList> getPlayLists(String userEmail, String tokenValue) {
        List<PlayList> playlist = new ArrayList<>();
        if(AuthenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            playlist = playlistRepo.findAll();
        }
        return playlist;
    }

    public String deletePlayList(String userEmail, String tokenValue, Integer id) {
        if(AuthenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            playlistRepo.deleteById(id);
            return "playlist deleted";
        }
        return "Unauthorised Access";
    }

    public String addSong(String userEmail, String tokenValue, Integer songId, Integer playlistId) {
        if(AuthenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            Optional<PlayList> playList = playlistRepo.findById(playlistId);
            Optional<Song> song = songRepo.findById(songId);
            List<Song> plSong = playList.get().getSongs();
            for(Song song1 : plSong){
                if(song1.getSongId().equals(songId)){
                    return "song already exists in playlist";
                }
            }
            playList.get().getSongs().add(song.get());
            return "song added";
        }
        return "Unauthorised Access";
    }

    public String removeSong(String userEmail, String tokenValue, Integer songId, Integer playlistId) {
        if(AuthenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            Optional<PlayList> playList = playlistRepo.findById(playlistId);
            List<Song> plSongs = playList.get().getSongs();
            for(Song song : plSongs){
                if(song.getSongId().equals(songId)){
                    plSongs.remove(song);
                    return "song is successfully removed from playlist";
                }
            }
            return "song doesn't exist";
        }
        return "Unauthorised Access";
    }
}
