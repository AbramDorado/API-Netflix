package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserTable;


public interface UserTableRepository extends JpaRepository<UserTable, Integer> {

	
}
