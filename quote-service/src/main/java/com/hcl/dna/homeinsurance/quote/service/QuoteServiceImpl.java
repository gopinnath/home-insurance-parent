package com.hcl.homeinsurance.quote.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.hcl.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.homeinsurance.quote.domain.QuoteEntity;
import com.hcl.homeinsurance.quote.dto.Response;
import com.hcl.homeinsurance.quote.exception.QuoteException;
import com.hcl.homeinsurance.quote.exception.SaveException;
import com.hcl.homeinsurance.quote.repository.QuoteRepository;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	@Autowired
	QuoteRepository quoteRepository;


	@Override
	public Response<?> calculateCoverageDetails(long propertyId) {
		// TODO Auto-generated method stub
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
