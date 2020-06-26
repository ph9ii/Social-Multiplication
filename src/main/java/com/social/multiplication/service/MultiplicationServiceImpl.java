package com.social.multiplication.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.social.multiplication.domain.Multiplication;
import com.social.multiplication.domain.MultiplicationResultAttempt;
import com.social.multiplication.domain.User;
import com.social.multiplication.repository.MultiplicationResultAttemptRepository;
import com.social.multiplication.repository.UserRepository;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;

	@Autowired
	public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
			final MultiplicationResultAttemptRepository attemptRepository, final UserRepository userRepository) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	// [...]

	@Transactional
	@Override
	public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
		// Check if the user already exists for that alias
		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

		// Avoid 'hack' attempts
		Assert.isTrue(!attempt.isCorrect(), "You can not send an attempt marked as correct!");

		boolean isCorrect = attempt.getResultAttempt() == attempt.getMultiplication().getFactorA()
				* attempt.getMultiplication().getFactorB();

		// Create a new copy, and setting 'correct' field accordingly
		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(attempt.getUser()),
				attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);

		// Store the attempt
		attemptRepository.save(checkedAttempt);

		return isCorrect;
	}

}
