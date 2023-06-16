package com.crio.jukebox.services;


import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

public class UserService implements IUserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void createUser(String name) {
		// TODO Auto-generated method stub
		User newUser = userRepository.save(new User(name));
		System.out.println(newUser.getId() + " " + newUser.getName());
	}

}
