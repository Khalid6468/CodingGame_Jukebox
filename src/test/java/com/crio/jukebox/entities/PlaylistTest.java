package com.crio.jukebox.entities;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


@DisplayName("PlaylistTest")
public class PlaylistTest {
    private List<Song> songs;
    private Playlist playlist;

    @BeforeEach
    public void setUp() throws Exception {
        songs = new ArrayList<>();
        songs.add(new Song("song1", "Tum Hi Ho",  null, null));
        songs.add(new Song("song2", "Channa Mereya", null, null));
        songs.add(new Song("song3", "Pal", null, null));
        playlist = new Playlist("playlist1", "Arijit Singh", songs);
    }

    @Test
    @DisplayName("checkIfSongExists should return true")
    public void checkIfSongExists_shouldReturnTrue_GivenSong() {
        Song song = songs.get(1);
        Assertions.assertTrue(playlist.checkIfSongExist(song));
    }

    @Test
    @DisplayName("checkIfSongExists should return false")
    public void checkIfSongExists_shouldReturnFalse_GivenSong() {
        Song song = new Song("song4", "Bekasoor", null, null);
        Assertions.assertFalse(playlist.checkIfSongExist(song));
    }
}
