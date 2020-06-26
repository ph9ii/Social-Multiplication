package com.social.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.social.multiplication.domain.Multiplication;
import com.social.multiplication.domain.MultiplicationResultAttempt;
import com.social.multiplication.domain.User;
import com.social.multiplication.repository.MultiplicationResultAttemptRepository;
import com.social.multiplication.repository.UserRepository;

public class MultiplicationServiceImplTest {
	private MultiplicationServiceImpl multiplicationServiceImpl;

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@Mock
	private MultiplicationResultAttemptRepository attemptRepository;

	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() {
		// with this call to initMocks we tell Mockito to process annotations
		MockitoAnnotations.initMocks(this);
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository,
				userRepository);
	}

	@Test
	public void createRandomMultiplicationTest() {
		// given (our mocked Random Generator service will return first 50, then 30
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		// when
		Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

		// assert
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		assertThat(multiplication.getResult()).isEqualTo(1500);
	}

	@Test
	public void checkCorrectAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("John_Doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
		given(userRepository.findByAlias("John_Doe")).willReturn(Optional.empty());

		// when
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

		// assert
		assertThat(attemptResult).isTrue();
		verify(attemptRepository).save(verifiedAttempt);
	}

	@Test
	public void checkWrongAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("John_Doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		given(userRepository.findByAlias("John_Doe")).willReturn(Optional.empty());

		// when
		boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

		// assert
		assertThat(attemptResult).isFalse();
		verify(attemptRepository).save(attempt);
	}
}
