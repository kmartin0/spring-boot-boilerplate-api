package com.boilerplate.boilerplateapi.features.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmailIgnoreCase(String email);
	Optional<User> findByUserNameIgnoreCase(String userName);
}
