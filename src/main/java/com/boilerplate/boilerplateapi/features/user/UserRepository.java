package com.boilerplate.boilerplateapi.features.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, ObjectId> {
	Optional<User> findByEmailIgnoreCase(String email);
	Optional<User> findByUserNameIgnoreCase(String userName);
}
