package com.expensetracker.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.expensetracker.entity.User;
import com.expensetracker.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUserById(int id) {
		
		 Optional<User> user =	userRepository.findById(id);
		 
		 if(user.isPresent()) {
			 return user.get();
		 }
		 else {
			 throw new RuntimeException("User not found for "+ id);
		 }
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public ResponseEntity<Object> saveUser(User user) {
		
		System.out.println("...................");
		
		User userExist = userRepository.findByUserName(user.getUserName());
		
		System.out.println("userExist:" + userExist);
		
		if(userExist != null) {
			 throw new RuntimeException("User already exist");
		}
		
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

	public void deleteById(int id) {

		userRepository.deleteById(id);
	}

	public User findUserByEmail(String email) {
		 return userRepository.findByEmail(email);
	}

	public void save(User user) {

		userRepository.save(user);
		
	}
}
