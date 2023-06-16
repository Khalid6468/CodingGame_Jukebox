package com.crio.jukebox.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crio.jukebox.entities.*;
import com.crio.jukebox.exceptions.*;
import com.crio.jukebox.repositories.UserRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@DisplayName("SongServiceTest")
public class SongServiceTest {
    @Mock 
    private UserRepository userRepository;

    @InjectMocks
    private SongService songService;

    @Test
    @DisplayName("playNextSong should play Next song in the currently active playlist")
    public void playNextSong_ShouldPlayNextSongInTheCurrentActivePlaylist() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
                add(new Song("3", "Safarname", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        user.setActivePlaylist("p1");
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act
        songService.playNextSong("1");

        // Assert
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("playPrevSong should play Previous song in the currently active playlist")
    public void playPrevSong_ShouldPlayPreviousSongInTheCurrentActivePlaylist() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
                add(new Song("3", "Safarnama", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        user.setActivePlaylist("p1");
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act
        songService.playPrevSong("1");

        // Assert
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("playSong should play song with a given Id in the currently active playlist")
    public void playPrevSong_ShouldPlaySongInTheCurrentActivePlaylist() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
                add(new Song("3", "Safarnama", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        user.setActivePlaylist("p1");
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act
        songService.playSong("1", "3");

        // Assert
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("playSong should throw SongNotFoundException when a non-existent songId is given")
    public void playPrevSong_ShouldThrowSongNotFoundException() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
                add(new Song("3", "Safarnama", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        user.setActivePlaylist("p1");
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act and Assert
        Assertions.assertThrows(SongNotFoundException.class, () -> songService.playSong("1", "4"));
        verify(userRepository, times(1)).getById(anyString());
    }
}