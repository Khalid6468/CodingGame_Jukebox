package com.crio.jukebox.repositories;

import java.util.List;
import java.util.HashMap;


import com.crio.jukebox.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class PlaylistRepositoryTest {
    private PlaylistRepository playlistRepository;

    @BeforeEach
    void setup() {
        Playlist p1 = new Playlist("playlist1", "Raghuvaran BTech", null);
        Playlist p2 = new Playlist("playlist2", "Athrangi", null);
        Playlist p3 = new Playlist("playlist3", "Orange", null);
        final HashMap<String, Playlist> playlistMap = new HashMap<String, Playlist>() {
            {
                put("playlist1", p1);
                put("playlist2", p2);
                put("playlist3", p3);
            }
        };
        playlistRepository = new PlaylistRepository(playlistMap);
    }

    @Test
    @DisplayName("save method should create and return new Playlist")
    public void savePlaylist() {
        Playlist p1 = new Playlist("playlist4", "Master", null);
        Playlist expectedPlaylist = new Playlist("playlist4", "Master", null);
        Playlist actualPlaylist = playlistRepository.save(p1);
        Assertions.assertEquals(expectedPlaylist, actualPlaylist);
    }


    @Test
    @DisplayName("getAll method should return all the playlists")
    public void getAll_ShouldReturnAllPlaylists() {
        int expectedNumber = 3;
        List<Playlist> playlists = playlistRepository.getAll();
        int actualNumber = playlists.size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    @DisplayName("getAll method should return Empty list if no Playlists are there")
    public void getAll_ShouldReturnEmptyList() {
        int expectedNumber = 0;
        PlaylistRepository emptyPlaylistRepository = new PlaylistRepository();
        List<Playlist> actualPlaylists = emptyPlaylistRepository.getAll();
        Assertions.assertEquals(expectedNumber, actualPlaylists.size());
    }

}