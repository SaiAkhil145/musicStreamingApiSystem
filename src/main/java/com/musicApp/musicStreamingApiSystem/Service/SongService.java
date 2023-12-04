package com.musicApp.musicStreamingApiSystem.Service;

import com.musicApp.musicStreamingApiSystem.Model.Song;
import com.musicApp.musicStreamingApiSystem.Repository.ISongRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    ISongRepo songRepo;
    public void addSong(Song song) {
        songRepo.save(song);
    }

    public List<Song> getAllSongs() {
        return (List<Song>) songRepo.findAll();
    }

    public String updateSong(Song song) {
        Integer id =song.getSongId();
        Song existingSong = songRepo.findById(id).orElse(null);
        if(existingSong!=null){
            existingSong.setSongName(song.getSongName());
            existingSong.setSongLink(song.getSongLink());
            existingSong.setSongArtist(song.getSongArtist());
            existingSong.setSongGenre(song.getSongGenre());
            existingSong.setSongId(song.getSongId());
            songRepo.save(existingSong);
            return "song updated succesfully";
        }
        return song.getSongName()+"not exists";
    }

    public String deleteSong(Integer id) {
        songRepo.deleteById(id);
        return "song deleted succesfully!";
    }
}
