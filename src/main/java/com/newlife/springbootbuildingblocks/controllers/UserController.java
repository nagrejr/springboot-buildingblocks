package com.newlife.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.newlife.springbootbuildingblocks.entities.User;
import com.newlife.springbootbuildingblocks.exceptions.UserAlreadyExistException;
import com.newlife.springbootbuildingblocks.exceptions.UserNameNotFoundException;
import com.newlife.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.newlife.springbootbuildingblocks.services.UserService;

@RestController
//for global exception handler validation
@Validated
//this requestmapping will keep the mapping user common for all class methods
//get or post or delete or update --> any mapping
@RequestMapping(value = "/users")
public class UserController {

	// autowired the service
	@Autowired
	private UserService userService;

	// getallusers method from service class
	@GetMapping//("/users") 
	//commented due to class requestmapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// postmapping for creating user
	// accept User input as a requestbody
	@PostMapping//("users")
	// UriComponentsBuilder - factory class for getting instances of UriComponents
	// which are helpful for constructing URIs
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {

		// user created 201 status code
		// implementation is only in controller, not in service
		try {
			userService.createUser(user);
			// http headers impl.
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// getuserbyid method
	//@GetMapping("/users/{id}") -->commented due to class requestmapping
	@GetMapping("/{id}")
	// path variable is to use id from mapping for input
	// min(1) for the minimum id should be 1 not 0
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		// try catch block to handle UserNotException
		try {
			return userService.getUserById(id);
		}
		// send HTTP status response of user not found
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@PutMapping("/{id}")
	// using pathvariable and requestbody as a input
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

		// try catch block to handle UserNotException
		try {
			return userService.updateuserById(id, user);
		}
		// send HTTP status response of user not found
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	// taking only id as a pathvariable input
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	// using byusername in mapping due to
	// if used directly username it will act as a id which is mentioned above for
	// getmapping
	// used username as a pathvariable input
	@GetMapping("users/byusername/{username}")
	// throws custom username not found exception
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {

		User user = userService.getUserByUsername(username);
		if (user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		return user;
	}
}
