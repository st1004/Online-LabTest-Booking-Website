package com.LabTest.LabTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LabTest.LabTest.entity.User;

@Repository
public interface Test1Repository extends JpaRepository<User,Integer> {
	
   @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
	User findByUsernameAndPassword(String username, String password);

}
