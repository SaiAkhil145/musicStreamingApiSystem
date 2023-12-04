package com.musicApp.musicStreamingApiSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer songId;
    @NotEmpty
    private String songName;
    @NotBlank
    private String songArtist;
    @NotEmpty(message = "song link should not be empty")
    private String songLink;
    @Enumerated(value = EnumType.STRING)
    private Genre songGenre;
}
