package com.social.multiplication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.social.multiplication.domain.User;

/**
 * This interface allows us to save and retrieve Users.
 * 
 * @author mocha
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByAlias(final String alias);
}
