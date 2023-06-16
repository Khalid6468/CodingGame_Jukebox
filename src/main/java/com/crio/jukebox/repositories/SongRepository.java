package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.jukebox.entities.Song;
public class SongRepository implements ISongRepository {

	private static SongRepository instance;
	private final HashMap<String, Song> songMap;
	
	private SongRepository(HashMap<String, Song> songMap) {
		this.songMap = songMap;
	}

	public static SongRepository getInstance() {
		return instance;
	}

	public static SongRepository getInstance(HashMap<String, Song> songMap) {
		if(instance == null) {
			instance = new SongRepository(songMap);
		}
		System.out.println("Songs Loaded successfully");
		return instance;
	}
	
	public boolean checkIfSongExists(String songId) {
		return songMap.containsKey(songId);
	}
	
	public List<Song> getByIds(List<String> songIds) {
		List<Song> results = new ArrayList<Song>();
		for(String songId: songIds) {
			Song s = getById(songId).orElseGet(null);
			if(s != null)
				results.add(s);
		}
		return results;
	}
	
	@Override
	public Song save(Song entity) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Song> getAll() {
		// TODO Auto-generated method stub
		return songMap == null ? null : songMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Song> getById(String id) {
		// TODO Auto-generated method stub
		return songMap.values().stream().filter(s -> s.getId().equals(id)).findFirst();
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Song entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
