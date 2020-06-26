package com.social.multiplication.repository;

import org.springframework.data.repository.CrudRepository;

import com.social.multiplication.domain.Multiplication;

/**
 * This interface allows us to save and retrieve Multiplications.
 * @author mocha
 *
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
