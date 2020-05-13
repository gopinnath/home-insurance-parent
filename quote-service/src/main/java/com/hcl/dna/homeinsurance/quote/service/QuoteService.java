package com.hcl.homeinsurance.quote.service;

import org.springframework.stereotype.Service;

import com.hcl.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.homeinsurance.quote.dto.Response;
import com.hcl.homeinsurance.quote.exception.QuoteException;
import com.hcl.homeinsurance.quote.exception.SaveException;

@Service
public interface QuoteService {

	Response<?> calculateCoverageDetails(long propertyId);

	Response<?> saveQuote(CoverageEntity coverageEntity) throws SaveException;

	Response<?> getQuote(long propertyId) throws QuoteException;
	
}