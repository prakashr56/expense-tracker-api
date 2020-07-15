package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensetracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	 User findByUserName(String userName);

	 User findByEmail(String email);
}
