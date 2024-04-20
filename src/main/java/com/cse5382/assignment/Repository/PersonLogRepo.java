package com.cse5382.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cse5382.assignment.Model.PersonLogModel;

public interface PersonLogRepo extends JpaRepository<PersonLogModel, Integer>{

}
