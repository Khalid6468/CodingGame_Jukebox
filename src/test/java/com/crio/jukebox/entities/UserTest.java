package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;


@DisplayName("UserTest")
public class UserTest {
    
    private HashMap<String, Playlist> playlists;
    User user;

    @BeforeEach
    public void setUp() throws Exception {
        playlists = new HashMap<>();
        playlists.put("playlist1", new Playlist("playlist1", "Pop", new ArrayList<>()));
        playlists.put("playlist2", new Playlist("playlist2", "Upbeat", new ArrayList<>()));
        playlists.put("playlist3", new Playlist("playlist3", "Rock", new ArrayList<>()));

        user = new User("user1", "username", playlists);
    }


    @Test
    @DisplayName("checkIfPlaylistExists should return true")
    public void checkIfPlaylistExists_shouldReturnTrue_GivenPlaylist() {
        Playlist playlist = playlists.get("playlist1");
        Assertions.assertTrue(user.checkIfPlaylistExists(playlist));
    }

    @Test
    @DisplayName("checkIfPlaylistExists should return false")
    public void checkIfPlaylistExists_shouldReturnFalse_GivenPlaylist() {
        Playlist playlist = new Playlist("playlist4", "newPlaylist", new ArrayList<>());
        Assertions.assertFalse(user.checkIfPlaylistExists(playlist));
    }
}
