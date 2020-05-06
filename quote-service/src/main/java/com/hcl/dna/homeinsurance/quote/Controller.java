package com.hcl.dna.homeinsurance.quote;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dna.homeinsurance.quote.domain.QuoteEntity;
import com.hcl.dna.homeinsurance.quote.service.QuoteService;

@RestController
@RequestMapping("api/quotes")
public class Controller {

	private final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	@Autowired
	private QuoteService service;
			
	@GetMapping("/")
	public QuoteEntity getUserByUsername()	{
		LOGGER.info("Inside getUserByUsername");
		Long userId = null;
		return service.getQuoteByUserId(userId);
	}
}
