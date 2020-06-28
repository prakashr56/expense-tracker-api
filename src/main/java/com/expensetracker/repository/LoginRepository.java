package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensetracker.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

	User findByUserNameAndPassword(String userName, String password);

}
