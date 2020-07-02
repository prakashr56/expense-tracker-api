package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.entity.User;
import com.expensetracker.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
	
		return userService.getUserById(id);
	}
	
	@GetMapping("userList")
	public List<User> getAllUser() {
		
		return userService.getAllUsers();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		
		System.out.println("userName: "+ user.getUserName() + " password: "+ user.getPassword());
		
		return userService.saveUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		
		userService.deleteById(id);
	}
}
