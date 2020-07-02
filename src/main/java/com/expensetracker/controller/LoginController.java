package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.entity.User;
import com.expensetracker.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/loginUser")
	public ResponseEntity<Object> loginUser(@RequestBody User user){
		
		ResponseEntity<Object> responseEntity = loginService.loginUser(user);
		
		return responseEntity;
	}
}
