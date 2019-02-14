package com.sidharth.hibernate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@Autowired
	public UserDataRepository userData;
	
	@GetMapping("/allUsers")
	public List<UserData> getAllUsers() {
		
		return userData.findAll();
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUserData(@RequestBody UserData user) {
		UserData savedUser = userData.save(user);
		return new ResponseEntity<Object>(savedUser.getUserID(), HttpStatus.OK);
	}
	
	@PutMapping("/updateUser/{userID}")
	public ResponseEntity<Object> updateUserData(@PathVariable("userID") Integer userID, @RequestBody UserData user) {
		
		Optional<UserData> optional = userData.findById(userID);
		if(!optional.isPresent()) {
			return new ResponseEntity<Object>("No Data Found to Update", HttpStatus.BAD_REQUEST);
		}
		user.setUserID(userID);
		userData.save(user);
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userID}")
	public ResponseEntity<String> deleteUserData(@PathVariable("userID") Integer userID) {
		
		Optional<UserData> optional = userData.findById(userID);
		if(!optional.isPresent()) {
			return new ResponseEntity<String>("No Data Found to Delete", HttpStatus.BAD_REQUEST);
		}
		
		userData.deleteById(userID);
		return new ResponseEntity<String>("User is Deleted", HttpStatus.OK);
	}
}
