package com.social.multiplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping
	ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(multiplicationService.getUserStats(alias));
	}

	@GetMapping("/{resultId}")
	ResponseEntity<Optional<MultiplicationResultAttempt>> getResultById(final @PathVariable("resultId") Long resultId) {
		return ResponseEntity.ok(multiplicationService.getResultById(resultId));
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
