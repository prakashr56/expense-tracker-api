package com.expensetracker.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.expensetracker.entity.UserExpense;
import com.expensetracker.repository.UserExpenseRepository;

@Service
public class UserExpenseService {

	@Autowired
	private UserExpenseRepository repository;
	
	public ResponseEntity<Object> saveUserExpense(UserExpense userExpense) {
		
		UserExpense savedUserExpense = repository.save(userExpense);				
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserExpense.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

	public ResponseEntity<Object> getExpenseByUserId(int userId) {
		
		List<UserExpense> userExpense = repository.findByUserId(userId);
		
		if(userExpense == null) {
			return new ResponseEntity<>("User expense not found", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(userExpense);
	}
	
	public ResponseEntity<Object> getExpenseByExpenseId(int id) {
			
			Optional<UserExpense> userExpense = repository.findById(id);
			
			if(userExpense == null) {
				return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
			}
			
			return ResponseEntity.ok(userExpense);
		}

	public ResponseEntity<Object> deleteExpenseByExpenseId(int id) {
		
		
		repository.deleteById(id);
		
		return null;
	}

}

