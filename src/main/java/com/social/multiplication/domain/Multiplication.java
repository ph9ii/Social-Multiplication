package com.social.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
@Entity
public final class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;

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
