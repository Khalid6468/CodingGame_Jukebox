package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;

public class PlaylistRepository implements IPlaylistRepository {
	
	private final HashMap<String, Playlist> playlistMap;
	private Integer playlistID = 0;
	
	public PlaylistRepository() {
		this.playlistMap = new HashMap<String, Playlist>();
	}
	
	public PlaylistRepository(HashMap<String, Playlist> playlistMap) {
		this.playlistMap = playlistMap;
		playlistID = playlistMap.size();
	}
	
	@Override
	public Playlist save(Playlist entity) {
		// TODO Auto-generated method stub
		if(playlistMap.containsKey(entity.getId())) {
			playlistMap.put(entity.getId(), entity);
		} else {
			playlistID++;
			Playlist newEntry = new Playlist(Integer.toString(playlistID), entity.getName(), entity.getSongs());
			playlistMap.put(newEntry.getId(), newEntry);
			return newEntry;
		}
		return entity;
	}

	@Override
	public List<Playlist> getAll() {
		// TODO Auto-generated method stub
		return playlistMap == null ? null : playlistMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Playlist> getById(String id) {
		// TODO Auto-generated method stub
		return playlistMap.values().stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		
		return playlistMap.containsKey(id);
	}

	@Override
	public void delete(Playlist entity) throws PlaylistNotFoundException{
		// TODO Auto-generated method stub
		if(!existsById(entity.getId())) {
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		playlistMap.remove(entity.getId());
		System.out.println("Delete Successful");
	}

	@Override
	public void deleteById(String id) throws PlaylistNotFoundException{
		// TODO Auto-generated method stub
		if(!existsById(id)) {
			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		playlistMap.remove(id);
		System.out.println("Delete Successful");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
