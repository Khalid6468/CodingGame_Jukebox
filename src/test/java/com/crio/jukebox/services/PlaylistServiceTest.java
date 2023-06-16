package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.anyList;
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
import com.crio.jukebox.repositories.SongRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;



@DisplayName("PlaylistServiceTest")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    @DisplayName("createPlaylist should throw SongNotFoundException when some of the songs are not present in the pool")
    public void createPlaylist_ShouldThrowSongNotFoundException() {
        // Arrange
        List<String> songIds = new ArrayList<String>(){
            {
                add("1");
                add("2");
                add("3");
            }
        };
        User user = new User("1", "Khalid", null);
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        
        // Act and Assert
        Assertions.assertThrows(SongNotFoundException.class, () -> playlistService.createPlaylist("1", "myPlaylist", songIds));
        verify(songRepository, times(1)).getByIds(anyList());
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("createPlaylist should create a new playlist when given a userId, playlistName and list of songs")
    public void createPlaylist_ShouldCreateAPlaylist() {
        // Arrange
        User user = new User("1", "khalid", null);
        List<String> songIds = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        when(songRepository.getByIds(anyList())).thenReturn(songs);

        // Act
        playlistService.createPlaylist("1", "myPlaylist", songIds);

        // Assert
        Assertions.assertTrue(user.checkIfPlaylistExists(new Playlist("1", "myPlaylist", songs)));
        verify(userRepository, times(1)).getById(anyString());
        verify(songRepository, times(1)).getByIds(anyList());
    }

    @Test
    @DisplayName("deletePlaylist should throw PlaylistNotFoundException, given a userId and and a nonexistent playlistId")
    public void deletePlaylist_ShouldThrowPlaylistNotFoundException() {
        // Arrange
        User user = new User("1", "Khalid", null);
        String playlistId = "1";
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act and Assert
        Assertions.assertThrows(PlaylistNotFoundException.class, () -> playlistService.deletePlaylist("1", playlistId));
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("deletePlaylist should delete the playlist given a userId and playlisId")
    public void deletePlaylist_ShouldDeletethePlaylist() {
        // Arrange
        User user = new User("1", "Khalid", null);
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        user.addPlaylist(new Playlist("1", "myPlaylist", songs));
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));

        // Act 
        playlistService.deletePlaylist("1", "1");

        // Assert
        Assertions.assertFalse(user.checkIfPlaylistExists(new Playlist("1", "myPlaylist", songs)));
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("addSongsToPlaylist should add list of songs to a user's playlist given the songIds")
    public void AddSongsToPlaylist_ShouldAddSongsToAUsersPlaylist() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        List<String> songToAddIds = new ArrayList<String> () {
            {
                add("3");
                add("4");
            }
        };
        List<Song> songsToAdd = new ArrayList<Song>() {
            {
                add(new Song("3", "Ninnila", null, null));
                add(new Song("4", "Kannuladha", null, null));
            }
        };
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        when(songRepository.getByIds(anyList())).thenReturn(songsToAdd);

        // Act
        playlistService.addSongsToPlaylist("1", "p1", songToAddIds);

        // Assert
        Assertions.assertEquals(user.getPlaylist("p1").orElseGet(null).size(), 4);
        verify(userRepository, times(1)).getById(anyString());
        verify(songRepository, times(1)).getByIds(anyList());
    }

    @Test
    @DisplayName("addSongsToPlaylist should not add a song if it is already present in the playlist")
    public void AddSongsToPlaylist_ShouldANotAddDuplicateSongs() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        User user = new User("1", "Khalid", playlists);
        List<String> songToAddIds = new ArrayList<String> () {
            {
                add("2");
                add("3");
            }
        };
        List<Song> songsToAdd = new ArrayList<Song>() {
            {
                add(new Song("2", "Gucche gulabi", null, null));
                add(new Song("3", "Kannuladha", null, null));
            }
        };
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        when(songRepository.getByIds(anyList())).thenReturn(songsToAdd);

        // Act
        playlistService.addSongsToPlaylist("1", "p1", songToAddIds);

        // Assert
        Assertions.assertEquals(user.getPlaylist("p1").orElseGet(null).size(), 3);
        verify(userRepository, times(1)).getById(anyString());
        verify(songRepository, times(1)).getByIds(anyList());
    }


    @Test
    @DisplayName("deleteSongsFromPlaylist should throw SongNotFoundException if any of the given songs are Not present in the playlist")
    public void deleteSongsFromPlaylist_ShouldThrowSongNotFoundException() {
        // Arrange
        
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        List<String> songIds = new ArrayList<String>() {
            {
                add("3");
                add("2");
            }
        };
        List<Song> songsToDelete = new ArrayList<Song>() {
            {
                add(new Song("3", "Cheliya", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        User user = new User("1", "Khalid", playlists);
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        when(songRepository.getByIds(anyList())).thenReturn(songsToDelete);

        // Act and Assert
        Assertions.assertThrows(SongNotFoundException.class, () -> playlistService.deleteSongsFromPlaylist("1", "p1", songIds));
        verify(userRepository, times(1)).getById(anyString());
        verify(songRepository, times(1)).getByIds(anyList());
    }

    @Test
    @DisplayName("deleteSongsFromPlaylist should Delete the given songs from the playlist")
    public void deleteSongsFromPlaylist_ShouldDeleteTheSongs() {
        // Arrange
        
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "inthandam", null, null));
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        Playlist myplaylist = new Playlist("p1", "my_playlist", songs);
        HashMap<String, Playlist> playlists = new HashMap<String, Playlist>() {
            {
                put("p1", myplaylist);
            }
        };
        List<String> songIds = new ArrayList<String>() {
            {
                add("2");
            }
        };
        List<Song> songsToDelete = new ArrayList<Song>() {
            {
                add(new Song("2", "Gucche gulabi", null, null));
            }
        };
        User user = new User("1", "Khalid", playlists);
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        when(songRepository.getByIds(anyList())).thenReturn(songsToDelete);

        // Act
        playlistService.deleteSongsFromPlaylist("1", "p1", songIds);

        // Assert
        Assertions.assertEquals(user.getPlaylist("p1").orElseGet(null).size(), 1);
        verify(userRepository, times(1)).getById(anyString());
        verify(songRepository, times(1)).getByIds(anyList());
    }

    @Test
    @DisplayName("playPlaylist should throw EmptyPlaylistException when given a playlist which has no songs")
    public void playPlaylist_ShouldThrowEmptyPlaylistException() {
        // Arrange
        Playlist myPlaylist = new Playlist("1", "my_playlist", null);
        HashMap<String, Playlist> myPlaylists = new HashMap<String, Playlist>() {
            {
                put("1", myPlaylist);
            }
        };
        User user = new User("1", "Khalid", myPlaylists);
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        
        // Act and Assert
        Assertions.assertThrows(EmptyPlaylistException.class, () -> playlistService.playPlaylist("1", "1"));
        verify(userRepository, times(1)).getById(anyString());
    }

    @Test
    @DisplayName("playPlaylist should play the first song in the playlist")
    public void playPlaylist_ShouldPlayFirstSongInThePlaylist() {
        // Arrange
        List<Song> songs = new ArrayList<Song>() {
            {
                add(new Song("1", "SabTera", null, null));
                add(new Song("2", "Pal", null, null));
            }
        };
        Playlist myPlaylist = new Playlist("1", "my_playlist", songs);
        HashMap<String, Playlist> myPlaylists = new HashMap<String, Playlist>() {
            {
                put("1", myPlaylist);
            }
        };
        User user = new User("1", "Khalid", myPlaylists);
        when(userRepository.getById(anyString())).thenReturn(Optional.of(user));
        // Act
        playlistService.playPlaylist("1", "1");
        // Assert
        verify(userRepository, times(1)).getById(anyString());
    }
}
