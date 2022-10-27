package com.newlife.springbootbuildingblocks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newlife.springbootbuildingblocks.entities.Order;
import com.newlife.springbootbuildingblocks.entities.User;
import com.newlife.springbootbuildingblocks.exceptions.UserNotFoundException;
import com.newlife.springbootbuildingblocks.repositories.UserRepository;

@RestController

//this requestmapping will keep the mapping user common for all class methods
//get or post or delete or update --> any mapping
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	// this getmapping is work as
	// "/users/{userId}/orders"
	@GetMapping(value = "/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		return userOptional.get().getOrders();
	}
}
