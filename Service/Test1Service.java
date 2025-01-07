package com.LabTest.LabTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LabTest.LabTest.entity.User;
import com.LabTest.LabTest.repository.Test1Repository;

@Service
public class Test1Service {
	
	@Autowired
	private Test1Repository tRepo;
	public void save(User u) {
		
		tRepo.save(u);
		
	}
	
	public User signIn(String username, String password) {
        return tRepo.findByUsernameAndPassword(username, password);
    }

}
