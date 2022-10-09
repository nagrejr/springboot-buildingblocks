package com.newlife.springbootbuildingblocks.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloWorldController {
	
	//can use any way
	//either requestmapping with get method or getmapping
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "hello world";
	}
	
	//bean --> gets a json object response from to string
	@GetMapping("/helloworld-bean")
	public UserDetails helloworldBean() {
		return new UserDetails("suraj", "nagre", "aurangabad");
	}

}
