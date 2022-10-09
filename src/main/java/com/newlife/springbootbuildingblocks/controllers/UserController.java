package com.newlife.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newlife.springbootbuildingblocks.entities.User;
import com.newlife.springbootbuildingblocks.services.UserService;

@RestController
public class UserController {
	
	//autowired the service
	@Autowired
	private UserService userService;

	//getallusers method from service class
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//postmapping for creating user
	//accept User input as a requestbody
	@PostMapping("users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//getuserbyid method
	@GetMapping("/users/{id}")
	//path variable is to use id from mapping for input
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	//using pathvariable and requestbody as a input
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateuserById(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	//taking only id as a pathvariable input
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//using byusername in mapping due to
	//if used directly username it will act as a id which is mentioned above for getmapping
	//used username as a pathvariable input
	@GetMapping("users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
}
