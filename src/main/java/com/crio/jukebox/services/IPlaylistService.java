package com.crio.jukebox.services;

import java.util.List;


import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.EmptyPlaylistException;

public interface IPlaylistService {
	public void createPlaylist(String userId, String playlistName, List<String>songIds) throws SongNotFoundException;
	public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException;
	public void addSongsToPlaylist(String userId, String playlistId, List<String>songIds) throws SongNotFoundException;
	public void deleteSongsFromPlaylist(String userId, String playlistId, List<String>songIds) throws SongNotFoundException;
	public void playPlaylist(String userId, String playlistId) throws EmptyPlaylistException;
}
