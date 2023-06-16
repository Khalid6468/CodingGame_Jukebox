package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Optional;

import com.crio.jukebox.entities.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class SongRepositoryTest {
    private SongRepository songRepository;

    @BeforeEach
    void setup() {
        final HashMap<String, Song> songMap = new HashMap<String, Song> () {
            {
                put("song1", new Song("song1", "Baitikochi chusthe", null, null));
                put("song2", new Song("song2", "Gaali Valuga", null, null));
                put("song3", new Song("song3", "Dhaga Dhaga", null, null));
            }
        };
        songRepository = SongRepository.getInstance(songMap);
    }

    @Test
    @DisplayName("getById method should return an existing Song")
    public void getById_ShouldReturnASong() {
        Song expectedSong = new Song("song2", "Gaali Valuga", null, null);
        Song actualSong = songRepository.getById("song2").orElseGet(null);
        Assertions.assertEquals(expectedSong, actualSong);
    }

    @Test
    @DisplayName("getById method should return a null value")
    public void getById_ShouldReturnANull() {
        Optional<Song> actualSong = songRepository.getById("song5");
        Assertions.assertTrue(actualSong.isEmpty());
    }


}