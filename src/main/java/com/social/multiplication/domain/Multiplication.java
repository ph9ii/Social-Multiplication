package com.social.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class represents the multiplication (A * B)
 * 
 * @author mocha
 *
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Multiplication {
	// Both factors
	private final int factorA;
	private final int factorB;

	// The result of A * B
	private int result;
	
	// Empty constructor for JSON (de)serialization
	Multiplication() {
		this(0, 0);
	}
	
	private void setResult() {
		result = factorA * factorB;
	}
	
	public int getResult() {
		setResult();
		return result;
	}
}
