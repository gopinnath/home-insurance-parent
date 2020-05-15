package com.hcl.dna.homeinsurance.quote.service;

import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.dna.homeinsurance.quote.dto.Response;
import com.hcl.dna.homeinsurance.quote.exception.QuoteException;
import com.hcl.dna.homeinsurance.quote.exception.SaveException;

@Service
public interface QuoteService {

	Response<?> calculateCoverageDetails(long propertyId);

	Response<?> saveQuote(CoverageEntity coverageEntity) throws SaveException;

	Response<?> getQuote(long propertyId) throws QuoteException;
	
}