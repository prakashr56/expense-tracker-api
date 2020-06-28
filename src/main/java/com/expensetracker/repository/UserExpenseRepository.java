package com.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensetracker.entity.UserExpense;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Integer> {

	List<UserExpense> findByUserId(int userId);

}
