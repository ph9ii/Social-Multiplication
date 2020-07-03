package com.social.multiplication.service;

import java.util.List;

import com.social.multiplication.domain.Multiplication;
import com.social.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {
	/**
	 * Generates a random {@link Multiplication} object.
	 * 
	 * @return a Multiplication object with randomly generated factors.
	 */
	Multiplication createRandomMultiplication();

	/**
	 * @return true if the attempt matches the result of the multiplication, false
	 *         otherwise.
	 */
	boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
	
	/**
	 * @return list of all users attempt results from DB.
	 */
	List<MultiplicationResultAttempt> getUserStats(final String alias);
}
