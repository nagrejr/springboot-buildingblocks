package com.newlife.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.newlife.springbootbuildingblocks.entities.User;
import com.newlife.springbootbuildingblocks.exceptions.UserAlreadyExistException;
import com.newlife.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.newlife.springbootbuildingblocks.repositories.UserRepository;

@Service
public class UserService {

	// autowird to repo
	@Autowired
	private UserRepository userRepository;

	// getallusers method
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// createuser method with return as a User
	// will take input as a User
	// to check user already exists or not - UserAlreadyExistException
	public User createUser(User user) throws UserAlreadyExistException{
		
		// user is already existed in repository or not
		User existingUser = userRepository.findUserByUsername(user.getUsername());
		
		// if existingUser is present in repo. 
		if(existingUser != null) {
			throw new UserAlreadyExistException("User is already exist, please try another username");
		}
		return userRepository.save(user);
	}

	// getuserbyid method
	// optional data type may or may not have the null value
	// using optional data type due to check whether the id is present or not
	// present
	// throws UserNotFoundException
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);

		// when id is not present it will throw an exception message
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in the repository");
		}
		return user;
	}

	// taking id as input to search the id exist or not
	// if exists then use the user object to update
	public User updateuserById(Long id, User user) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);

		// when id is not present it will throw an exception message
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in the repository, provide correct id");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	// not returning any data so return type is void
	// first have to check whether the id is present or not
	public void deleteUserById(Long id) {

		// no need for throws UserNotFoundException in the method definition
		Optional<User> optionalUser = userRepository.findById(id);

		// when id is not present it will throw an exception message
		// no need for ResponseStatusException in controller class
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in the repository, provide correct id");
		}
		
		userRepository.deleteById(id);
	}

	// using string username as a input
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
}
