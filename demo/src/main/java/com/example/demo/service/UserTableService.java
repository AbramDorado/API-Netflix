package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserTable;
import com.example.demo.repository.UserTableRepository;

@Service
public class UserTableService {

	@Autowired 
	UserTableRepository usertablerepo;
	
	 public UserTable getUserById(Integer userId) {
	        return usertablerepo.findById(userId).orElse(null);
	    }

	    public void saveUser(UserTable user) {
	        usertablerepo.save(user);
	    }

}
