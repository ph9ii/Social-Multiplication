package com.social.multiplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.multiplication.domain.MultiplicationResultAttempt;
import com.social.multiplication.service.MultiplicationService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {

	private final MultiplicationService multiplicationService;

	MultiplicationResultAttemptController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}

	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(
			@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
		boolean correct = multiplicationService.checkAttempt(multiplicationResultAttempt);

		// Create a new copy, with correct
		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
				multiplicationResultAttempt.getUser(), multiplicationResultAttempt.getMultiplication(),
				multiplicationResultAttempt.getResultAttempt(), correct);
		return ResponseEntity.ok(checkedAttempt);
	}

	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	@Getter
	static final class ResultResponse {
		private final boolean correct;
	}
}
