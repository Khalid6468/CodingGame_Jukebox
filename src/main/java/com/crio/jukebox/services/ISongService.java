package com.crio.jukebox.services;

import com.crio.jukebox.exceptions.SongNotFoundException;

public interface ISongService {
	public void playNextSong(String userId);
	public void playPrevSong(String userId);
	public void playSong(String userId, String songId) throws SongNotFoundException;
}
