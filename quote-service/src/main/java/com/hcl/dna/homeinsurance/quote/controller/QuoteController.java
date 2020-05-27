package com.hcl.dna.homeinsurance.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dna.homeinsurance.quote.domain.QuoteAggregateRoot;
import com.hcl.dna.homeinsurance.quote.dto.Response;
import com.hcl.dna.homeinsurance.quote.exception.QuoteException;
import com.hcl.dna.homeinsurance.quote.exception.SaveException;
import com.hcl.dna.homeinsurance.quote.service.QuoteService;

@RestController
@RequestMapping("api/quotes")
public class QuoteController {
	
	  @Autowired QuoteService quoteService;
	
	
	@GetMapping("/calculate/{propertyId}")
	public ResponseEntity<Response<?>> calculateCoverageDetails(@PathVariable("propertyId") long propertyId) 
	{
		return new ResponseEntity<>(quoteService.calculateCoverageDetails(propertyId),HttpStatus.FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<Response<?>> saveQuote(@RequestBody QuoteAggregateRoot quoteAggregateRoot) throws SaveException 
	{
		return new ResponseEntity<>(quoteService.saveQuote(quoteAggregateRoot),HttpStatus.FOUND);
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<Response<?>> getQuoteByPropertyId(@PathVariable("propertyId") long propertyId) throws QuoteException 
	{
		return new ResponseEntity<>(quoteService.getQuoteByPropertyId(propertyId),HttpStatus.FOUND);
	}
	
	@GetMapping("quoteId/{quoteId}")
	public ResponseEntity<Response<?>> getQuoteByQuoteId(@PathVariable("quoteId") long quoteId) throws QuoteException 
	{
		return new ResponseEntity<>(quoteService.getQuoteByQuoteId(quoteId),HttpStatus.FOUND);
	}
	
	
}