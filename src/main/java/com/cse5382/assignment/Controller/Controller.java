package com.cse5382.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cse5382.assignment.Model.PhoneBookEntry;
import com.cse5382.assignment.Service.PersonLogService;
import com.cse5382.assignment.Service.PhoneBookService;
import com.cse5382.assignment.util.Validator;

@RestController
public class Controller {
	@Autowired
	PhoneBookService phoneBookService;

	@Autowired
	PersonLogService personLogService;

	@GetMapping(path = "phoneBook/list")
	public List<PhoneBookEntry> list() {
		personLogService.addLog("LIST", "-");
		return phoneBookService.list();
	}

	@PostMapping(path = "phoneBook/add")
	public ResponseEntity<?> add(@RequestBody PhoneBookEntry phoneBookEntry) {
		try {
			if(Validator.isValidForPhoneNumber(phoneBookEntry.getPhoneNumber())== false){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			else {
				phoneBookService.add(phoneBookEntry);
				personLogService.addLog("ADD", phoneBookEntry.getName());
			}//END OF ELSE 
		} catch (Exception e) {
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "phoneBook/deleteByName")
	public ResponseEntity<?> deleteByName(@RequestParam String name) {
		try {
			if (phoneBookService.getByName(name)) {
				phoneBookService.deleteByName(name);
				personLogService.addLog("REMOVE", name);

			}else {
				return ResponseEntity.notFound().build();
			}
		
		} catch (Exception e) {
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "phoneBook/deleteByNumber")
	public ResponseEntity<?> deleteByNumber(@RequestParam String number) {

		try {
			if (phoneBookService.getByNumber(number)) {
				phoneBookService.deleteByNumber(number);

				personLogService.addLog("REMOVE", number);
			}else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
