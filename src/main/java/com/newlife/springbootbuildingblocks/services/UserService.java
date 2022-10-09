package com.newlife.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlife.springbootbuildingblocks.entities.User;
import com.newlife.springbootbuildingblocks.repositories.UserRepository;

@Service
public class UserService {
	
	//autowird to repo
	@Autowired
	private UserRepository userRepository;
	
	//getallusers method 
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//createuser method with return as a User
	//will take input as a User
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//getuserbyid method
	//optional data type may or may not have the null value
	//using optional data type due to check whether the id is present or not present
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	//taking id as input to search the id exist or not
	//if exists then use the user object to update 
	public User updateuserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//not returning any data so return type is void
	//first have to check whether the id is present or not
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	//using string username as a input
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
}
