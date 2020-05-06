package com.hcl.dna.homeinsurance.quote.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.quote.domain.QuoteEntity;
import com.hcl.dna.homeinsurance.quote.jpa.QuoteRepository;


@Service
public class QuoteServiceImplementation implements QuoteService {

	private final Logger LOGGER = Logger.getLogger(QuoteServiceImplementation.class.getName());
	
	@Autowired
	private QuoteRepository homeOwnerRepo;

	@Override
	public QuoteEntity getQuoteByUserId(Long userId) {
		LOGGER.info("Inside getQuoteByUserId");
		QuoteEntity quote = new QuoteEntity();
		homeOwnerRepo.findByUserId(userId);
		return quote;
	}
}
