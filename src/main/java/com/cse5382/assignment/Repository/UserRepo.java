package com.cse5382.assignment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cse5382.assignment.Model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Integer> {


	Optional<UserModel> findByUserNameAndPassword(String userName, String password);

	Optional<UserModel> findByToken(String token);

}
