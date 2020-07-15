package com.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.User;
import com.expensetracker.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;

	public ResponseEntity<Object> loginUser(User user) {
	
		User userDetails = loginRepository.findByEmailAndPasswordAndActive(user.getEmail(), user.getPassword(), true);
		
		if(userDetails == null) {
			return new ResponseEntity<>(user.getUserName() +" not found", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(userDetails);
	}
}
