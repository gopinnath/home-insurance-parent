package com.hcl.homeinsurance.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.homeinsurance.quote.dto.Response;
import com.hcl.homeinsurance.quote.exception.QuoteException;
import com.hcl.homeinsurance.quote.exception.SaveException;
import com.hcl.homeinsurance.quote.service.QuoteService;

@RestController
@RequestMapping("api/quote")
public class QuoteController {
	
	  @Autowired QuoteService quoteService;
	
	
	@GetMapping("calculate/{propertyId}")
	public ResponseEntity<Response<?>> calculateCoverageDetails(@PathVariable("propertyId") long propertyId) 
	{
		return new ResponseEntity<>(quoteService.calculateCoverageDetails(propertyId),HttpStatus.FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<Response<?>> saveQuote(@RequestBody CoverageEntity coverageEntity) throws SaveException 
	{
		return new ResponseEntity<>(quoteService.saveQuote(coverageEntity),HttpStatus.FOUND);
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<Response<?>> getQuote(@PathVariable("propertyId") long propertyId) throws QuoteException 
	{
		return new ResponseEntity<>(quoteService.getQuote(propertyId),HttpStatus.FOUND);
	}
	
	
}