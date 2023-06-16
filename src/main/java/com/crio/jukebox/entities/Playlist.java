package com.crio.jukebox.entities;


import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.crio.jukebox.exceptions.EmptyPlaylistException;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class Playlist extends BaseEntity {
	private List<Song> songs;
	private int songIdx = 0;
	private boolean active = false;
	
	public Playlist(Playlist other) {
		this(other.id, other.name, other.songs);
	}
	
	public Playlist(String name, List<Song> songs) {
		this.name = name;
		this.songs = songs;
		if(this.songs == null) {
			this.songs = new ArrayList<Song>();
		}
	}
	
	public Playlist(String Id, String name, List<Song>songs) {
		this.id = Id;
		this.name = name;
		this.songs = songs;
		if(this.songs == null) {
			this.songs = new ArrayList<Song>();
		}
	}

	public List<Song> getSongs() {
		return songs == null ? null : songs.stream().collect(Collectors.toList());
	}
	
	public void addSongs(List<Song> songs) {
		for(Song s: songs) {
			addSong(s);
		}
		print();
	}
	
	public void addSong(Song s) {
		for(Song song: songs) {
			if(song.getId().equals(s.getId())) {
				return;
			}
		}
		songs.add(s);
		return;
	}
	
	public void deleteSongs(List<Song> songs) throws SongNotFoundException {
		if(!checkIfAllSongsExist(songs)) {
			throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
		}
		for(Song s: songs) {
			deleteSong(s);
		}
		print();
	}
	
	public void deleteSong(Song s) {
		for(int i=0;i<songs.size();i++) {
			if(songs.get(i).getId().equals(s.getId())) {
				songs.remove(i);
				return;
			}
		}
	}
	
	public void play() throws EmptyPlaylistException {
		if(songs.isEmpty()) {
			throw new EmptyPlaylistException("Playlist is empty");
		}
		active = true;
		songs.get(0).play();
	}
	
	public void playSong(String songId) throws SongNotFoundException {
		for(int i=0;i<songs.size();i++) {
			if(songs.get(i).getId().equals(songId)) {
				songIdx = i; 
				songs.get(i).play();
				return ;
			}
		}
		throw new SongNotFoundException("Song Not Found in the current active playlist");
	}
	
	public void nextSong() {
		int playlistSize = songs.size();
		songIdx++;
		songIdx %= playlistSize;
		songs.get(songIdx).play();
	}
	
	public void prevSong() {
		int playlistSize = songs.size();
		songIdx--;
		if(songIdx < 0) 
			songIdx += playlistSize;
		songs.get(songIdx).play();
	}
	
	public boolean checkIfSongExist(Song other) {
		for(Song s: songs) {
			if(s.getId().equals(other.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfAllSongsExist(List<Song> other) {
		for(Song s: other) {
			boolean found = false;
			for(Song cur: songs) {
				if(cur.getId().equals(s.getId())) {
					found = true;
					break;
				}
			}
			if(!found) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		return songs.size() == 0;
	}

	public int size() {
		return songs.size();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive() {
		active = true;
	}
	
	public void setInactive() {
		songIdx = 0;
		active = false;
	}

	public void print() {
		System.out.println("Playlist ID - " + id);
		System.out.println("Playlist Name - " + name);
		System.out.print("Song IDs -");
		for(Song s: songs) {
			System.out.print(" " + s.getId());
		}
		System.out.println();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(songs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Playlist)) {
			return false;
		}
		Playlist other = (Playlist) obj;
		return Objects.equals(songs, other.songs);
	}

	@Override
	public String toString() {
		return "Playlist [songs=" + songs + ", id=" + id + ", name=" + name + "]";
	}

	
}
