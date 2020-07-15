package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensetracker.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	User findByEmailAndPasswordAndActive(String email, String password, Boolean active);

}
