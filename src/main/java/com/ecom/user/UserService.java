package com.ecom.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserAccount> getUserAll() {
		return userRepository.findAll();
	}

	public UserAccount editUser(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createUser(UserAccount userAccount) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUserByID(Long userID) {
		// TODO Auto-generated method stub
		
	}


	
}
