package com.social.multiplication.domain;

/**
 * This class represents the multiplication
 * 
 * @author mocha
 *
 */
public class Multiplication {
	// Both factors
	private int factorA;
	private int factorB;

	// The result of A * B
	private int result;

	public Multiplication(int factorA, int factorB) {
		super();
		this.factorA = factorA;
		this.factorB = factorB;
		this.result = factorA * factorB;
	}

	public int getFactorA() {
		return factorA;
	}

	public int getFactorB() {
		return factorB;
	}

	public int getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "Multiplication [factorA=" + factorA + ", factorB=" + factorB + ", result(A*B)=" + result + "]";
	}

}
