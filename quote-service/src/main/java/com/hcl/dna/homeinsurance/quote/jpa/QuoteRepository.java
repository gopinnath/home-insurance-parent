package com.hcl.dna.homeinsurance.quote.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

	Optional<Quote> findByUserId(Long userId);
	
}