
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return this.userService.addUser(user);
	}

	
	@GetMapping("/getUserList")
	public List<User> getUserList() {
		return this.userService.getUserList();
	}

	
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable("userId") int id) {
		return this.userService.getUser(id);
	}

	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		return this.userService.updateUser(user);
	}

	
	@DeleteMapping("/deleteUser/{userId}")
	public int deleteCourse(@PathVariable("userId") int id) {
		try {
			int deletedId = userService.deleteUser(id);
			return deletedId;
		} catch (Exception e) {
			return 0;
		}
	}

}
