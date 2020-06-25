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
	ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
		return ResponseEntity.ok(new ResultResponse(multiplicationService.checkAttempt(multiplicationResultAttempt)));
	}

	@RequiredArgsConstructor
	@NoArgsConstructor(force = true)
	@Getter
	static final class ResultResponse {
		private final boolean correct;
	}
}
