package com.crio.jukebox.services;

import java.util.List;
import java.util.ArrayList;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.*;

import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
public class PlaylistService implements IPlaylistService {
	
	private final SongRepository songRepository;
	private final UserRepository userRepository;
	
	public PlaylistService(SongRepository songRepository, UserRepository userRepository) {
		this.songRepository = songRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void createPlaylist(String userId, String playlistName, List<String> songIds) throws  SongNotFoundException {
		// TODO Auto-generated method stub
		List<Song> songs = new ArrayList<Song>();
		User user = userRepository.getById(userId).orElseGet(null);
		songs = songRepository.getByIds(songIds);
		if(songs.size() != songIds.size()) {
			throw new SongNotFoundException("Some of the requested songs are not available in the pool. Please try again.");
		}
		user.addPlaylist(new Playlist(playlistName, songs));
	}

	@Override
	public void deletePlaylist(String userId, String playlistId) throws PlaylistNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		user.deletePlaylist(playlistId);
	}

	@Override
	public void addSongsToPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException {
		// TODO Auto-generated method stub
		List<Song> songs = new ArrayList<Song>();
		songs = songRepository.getByIds(songIds);
		User user = userRepository.getById(userId).orElseGet(null);
		if(songs.size() != songIds.size()) {
			throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
		}
		Playlist playlist = user.getPlaylist(playlistId).orElseGet(null);
		playlist.addSongs(songs);
	}

	@Override
	public void deleteSongsFromPlaylist(String userId, String playlistId, List<String> songIds) throws SongNotFoundException{
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		Playlist p = user.getPlaylist(playlistId).orElseGet(null);
		List<Song> songs = new ArrayList<Song>();
		try {
			songs = songRepository.getByIds(songIds);
		} catch(SongNotFoundException ex) {
			throw new SongNotFoundException("Some songs not present in the pool. Please try again");
		}
		p.deleteSongs(songs);
	}

	@Override
	public void playPlaylist(String userId, String playlistId) throws EmptyPlaylistException {
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		Playlist p = user.getPlaylist(playlistId).orElseGet(null);
		p.play();
		user.setActivePlaylist(p.getId());
	}
	
}
