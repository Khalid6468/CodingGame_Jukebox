package com.crio.jukebox.services;


import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.UserRepository;


public class SongService implements ISongService {

	private final UserRepository userRepository;
	
	public SongService(UserRepository userRepo) {
		userRepository = userRepo;
	}

	@Override
	public void playNextSong(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		Playlist p = user.getPlaylist(user.getActivePlaylist()).orElseGet(null);
		p.nextSong();
	}

	@Override
	public void playPrevSong(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		Playlist p = user.getPlaylist(user.getActivePlaylist()).orElseGet(null);
		p.prevSong();
	}

	@Override
	public void playSong(String userId, String songId) throws SongNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getById(userId).orElseGet(null);
		Playlist p = user.getPlaylist(user.getActivePlaylist()).orElseGet(null);
		try{
			p.playSong(songId);
		} catch (SongNotFoundException e) {
			System.out.println("Given song id is not a part of the active playlist");
		}
	}

}
