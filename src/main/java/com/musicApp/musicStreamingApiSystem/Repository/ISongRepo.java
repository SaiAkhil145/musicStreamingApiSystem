package com.musicApp.musicStreamingApiSystem.Repository;

import com.musicApp.musicStreamingApiSystem.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepo extends JpaRepository<Song,Integer> {
}
