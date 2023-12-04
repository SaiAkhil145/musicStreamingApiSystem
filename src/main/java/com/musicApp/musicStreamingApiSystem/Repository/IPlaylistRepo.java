package com.musicApp.musicStreamingApiSystem.Repository;

import com.musicApp.musicStreamingApiSystem.Model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistRepo extends JpaRepository<PlayList,Integer> {
}
