package com.crio.jukebox.repositories;

import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {
	
	private final HashMap<String, User> userMap;
	private int ID = 0;
	
	public UserRepository() {
		userMap = new HashMap<>();
	}
	
	public UserRepository(HashMap<String, User> userMap) {
		this.userMap = userMap;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		if(entity.getId() != null && existsById(entity.getId())) {
			userMap.put(entity.getId(), entity);
		} else {	
			ID++;
			User newUser = new User(Integer.toString(ID), entity.getName(), entity.getPlaylists());
			userMap.put(newUser.getId(), newUser);
			entity = newUser;
		}
		return entity;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getById(String id) {
		// TODO Auto-generated method stub
		return userMap.values().stream().filter(user -> user.getId().equals(id)).findFirst();
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return userMap.containsKey(id);
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(String id){
		// TODO Auto-generated method stub

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
