package com.hcl.dna.homeinsurance.quote.service;

import com.hcl.dna.homeinsurance.quote.domain.QuoteEntity;

public interface QuoteService {
	
	public QuoteEntity getQuoteByUserId(Long userId);
	
}
