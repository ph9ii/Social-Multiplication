package com.social.multiplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.social.multiplication.domain.MultiplicationResultAttempt;

/**
 * This interface allows us to store and retrieve ResultAttemps.
 * 
 * @author mocha
 *
 */
public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {
	/**
	 * @return latest 5 attempts of a given user, identified by their alias.
	 */
	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
