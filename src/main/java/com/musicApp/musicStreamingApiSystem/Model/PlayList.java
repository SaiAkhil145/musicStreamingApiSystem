package com.musicApp.musicStreamingApiSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;
    @NotBlank(message = "playlist should not be empty")
    private String playlistName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="playList_song_table",joinColumns = @JoinColumn(name="playlist_id"),inverseJoinColumns = @JoinColumn(name="song_id"))
    private List<Song> songs;
}
