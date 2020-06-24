package com.social.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.multiplication.service.MultiplicationService;

/**
 * This class implements the REST API for the multiplication application.
 * 
 * @author mocha
 *
 */
@RestController
@RequestMapping("/multiplications")
final class MultiplicationController {
	private final MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationController(final MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}
}
