package com.expensetracker.controller;

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

import com.expensetracker.entity.UserExpense;
import com.expensetracker.service.UserExpenseService;

@RestController
@RequestMapping("/expense")
@CrossOrigin("*")
public class UserExpenseController {
	
	@Autowired
	private UserExpenseService service;

	@PostMapping("/save")
	public ResponseEntity<Object> saveExpense(@RequestBody UserExpense userExpense){
		
		System.out.println("expense save user" + userExpense.getUserId());
		
		return service.saveUserExpense(userExpense);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getExpenseByUserId(@PathVariable int userId){
		
		System.out.println("userId: "+ userId);
		
		return service.getExpenseByUserId(userId);
	}
	
	@GetMapping("/userexpense/{id}")
	public ResponseEntity<Object> getExpenseByExpenseId(@PathVariable int id){
		
		System.out.println("expenseId: "+ id);
		
		return service.getExpenseByExpenseId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteExpenseByExpenseId(@PathVariable int id){
		
		System.out.println("expenseId: "+ id);
		
		return service.deleteExpenseByExpenseId(id);
	}
	
}
