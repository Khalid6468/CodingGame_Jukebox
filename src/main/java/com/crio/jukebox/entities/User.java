package com.crio.jukebox.entities;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import com.crio.jukebox.exceptions.PlaylistNotFoundException;

public class User extends BaseEntity {
	private final Map<String, Playlist> playlists;
	private int playlistID = 0;
	private String activePlaylist = null;
	
	public User(User other) {
		this(other.id, other.name, other.playlists);
	}
	
	public User(String name) {
		this.name = name;
		playlists = new HashMap<String, Playlist>();
	}
	
	public User(String Id, String name, Map<String, Playlist> playlists) {
		this.id = Id;
		this.name = name;
		if(playlists != null)
			this.playlists = playlists;
		else 
			this.playlists = new HashMap<String, Playlist>();	
	}
	
	public void addPlaylist(Playlist playlist) {
		playlistID++;
		Playlist nplaylist = new Playlist(Integer.toString(playlistID), playlist.getName(), playlist.getSongs());
		playlists.put(Integer.toString(playlistID), nplaylist);
		System.out.println("Playlist ID - " + playlistID);
	}
	
	public void deletePlaylist(String playlistId) throws PlaylistNotFoundException {
		if(!playlists.containsKey(playlistId)) {
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		if(activePlaylist == playlistId) {
			activePlaylist = null;
		}
		playlists.remove(playlistId);
		System.out.println("Delete Successful");
	}

	public Optional<Playlist> getPlaylist(String playlistId) {
		return playlists.values().stream().filter(p -> p.getId().equals(playlistId)).findFirst();
	}
	
	public String getActivePlaylist() {
		return activePlaylist;
	}
	
	public void setActivePlaylist(String playlistId) {
		if(activePlaylist != null) {
			Playlist p = playlists.get(playlistId);
			p.setInactive();
		}
		activePlaylist = playlistId;
	}
	
	public boolean isActive(String playlistId) {
		if(activePlaylist == null)
			return false;
		return activePlaylist.equals(playlistId);
	}

	public boolean checkIfPlaylistExists(Playlist p) {
		return playlists.containsKey(p.getId());
	}

    public Map<String, Playlist> getPlaylists() {
		return playlists;
	}
	
	@Override
	public String toString() {
		return "User [playlists=" + playlists + ", id=" + id + ", name=" + name + "]";
	}
	
}
