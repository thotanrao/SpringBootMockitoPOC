package com.thota.mockito.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thota.mockito.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	


}
