package com.cse5382.assignment.Service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cse5382.assignment.Model.PersonLogModel;
import com.cse5382.assignment.Repository.PersonLogRepo;

@Service
public class PersonLogService {

	@Autowired
	PersonLogRepo personLogRepo;

	public void addLog(String operationType, String personName) {

		PersonLogModel log = new PersonLogModel();
		log.setOperationType(operationType);
		log.setPersonName(personName);
		log.setTime(new Timestamp(System.currentTimeMillis()));
		personLogRepo.save(log);

	}

}
