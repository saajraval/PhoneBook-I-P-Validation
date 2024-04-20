package com.cse5382.assignment.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cse5382.assignment.Model.UserModel;
import com.cse5382.assignment.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	public void save(UserModel user) {
		userRepo.save(user);
	}

	public UserModel getUserByUserNameAndPassword(String userName, String password) {
		Optional<UserModel> userOptional = userRepo.findByUserNameAndPassword(userName, password);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			return null;
		}
	}

	public String generateToken(int size) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(size);

		for (int i = 0; i < size; i++) {

			int index = (int) (alphaNumericString.length() * Math.random());

			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public UserModel getUserByToken(String token) {
		Optional<UserModel> userOptional = userRepo.findByToken(token);
		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			return null;
		}
	}
}
