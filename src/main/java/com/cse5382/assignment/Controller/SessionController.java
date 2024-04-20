package com.cse5382.assignment.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cse5382.assignment.Model.UserModel;
import com.cse5382.assignment.Service.UserService;

@Controller
@RequestMapping("public")
public class SessionController {

	@Autowired
	UserService userService;

	@PostMapping(path = "user/add")
	public ResponseEntity<?> add(@RequestBody UserModel userModel) {
		try {
			userService.save(userModel);
		} catch (Exception e) {
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "user/login")
	public ResponseEntity<?> login(@RequestBody UserModel userModel) {
		String token = "";
		try {
			UserModel dbUser = userService.getUserByUserNameAndPassword(userModel.getUserName(),
					userModel.getPassword());

			if (dbUser == null) {
				return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);

			} else {
				token = userService.generateToken(30);
				dbUser.setToken(token);
				userService.save(dbUser);
			}

		} catch (Exception e) {
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		return ResponseEntity.ok(map);
	}

}
