package com.hcl.dna.homeinsurance.quote.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.dna.homeinsurance.quote.domain.QuoteEntity;
import com.hcl.dna.homeinsurance.quote.dto.Response;
import com.hcl.dna.homeinsurance.quote.exception.QuoteException;
import com.hcl.dna.homeinsurance.quote.exception.SaveException;
import com.hcl.dna.homeinsurance.quote.repository.QuoteRepository;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	@Autowired
	QuoteRepository quoteRepository;


	@Override
	public Response<?> calculateCoverageDetails(long propertyId) {
		return CoverageEntity.newFactory(quoteRepository).getPropertyByPropertyId(propertyId);
	}


	@Override
	public Response<?> saveQuote(CoverageEntity coverageEntity) throws SaveException {
		return QuoteEntity.newFactory(quoteRepository).saveQuote(coverageEntity);
	}


	@Override
	public Response<?> getQuote(long propertyId) throws QuoteException {
		return QuoteEntity.newFactory(quoteRepository).getQuote(propertyId);
	}

	

}
