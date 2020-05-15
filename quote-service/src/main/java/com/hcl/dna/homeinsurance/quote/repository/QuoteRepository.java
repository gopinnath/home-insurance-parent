package com.hcl.dna.homeinsurance.quote.repository;



import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.hcl.dna.homeinsurance.quote.entity.Quote;



@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

	Optional<Quote> findByPropertyId(long propertyId);


}