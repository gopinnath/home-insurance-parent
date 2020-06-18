package com.hcl.dna.homeinsurance.quote.service;

import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.dna.homeinsurance.quote.domain.CoverageEntity;
import com.hcl.dna.homeinsurance.quote.domain.CoverageVO;
import com.hcl.dna.homeinsurance.quote.domain.QuoteAggregateRoot;
import com.hcl.dna.homeinsurance.quote.dto.PropertyEntity;
import com.hcl.dna.homeinsurance.quote.dto.Response;
import com.hcl.dna.homeinsurance.quote.entity.Quote;
import com.hcl.dna.homeinsurance.quote.exception.QuoteException;
import com.hcl.dna.homeinsurance.quote.exception.SaveException;
import com.hcl.dna.homeinsurance.quote.repository.QuoteRepository;
import com.hcl.dna.homeinsurance.quote.utility.QuoteUtility;

@Service
public class QuoteServiceImpl implements QuoteService {
	
	private static final String HEADER_NAME_AUTH = "Authorization";
	
	@Autowired
	private QuoteRepository quoteRepository;

	@Value("${spring.microservice.property}")
	private String propertyServiceUrl;
	
	

	@Override
	public Response<?> calculateCoverageDetails(long propertyId) {
		Response responseDto = new Response<>();
		PropertyEntity currentProperty =  getPropertyEntityByPropertyId(propertyId);
		
		double value =  Double.parseDouble(currentProperty.getPropertyInformationVO().getValueOfHome());
		int year = Integer.parseInt(currentProperty.getPropertyInformationVO().getYearWasBuilt());
		int footage = Integer.parseInt(currentProperty.getPropertyInformationVO().getSquareFootage());
		
		int homeValue = footage*120;
		
		int ALC = 5000;
		double deductable = (int) (.01*homeValue);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int difference = currentYear-year;
		double reduction=0;
		if(difference<5) {
			reduction = (.1*homeValue);
		}else if(difference<10){
			reduction =  (.2*homeValue);
		}else if(difference<20) {
			reduction =  (.3*homeValue);
		}else if(difference>20) {
			reduction= (.5*homeValue);
		}
		double dweleingCovereage = (double) (.50*value+homeValue);
		double rate = (double) 0.005;
		double detacheStructure = (double) (.10*dweleingCovereage);
		double personalProperty = (double) (.60*dweleingCovereage);
		double living = (double) (.20*dweleingCovereage);
		double premium1 =  (rate*homeValue);
	String message = currentProperty.getPropertyInformationVO().getDwellingStyle();
	double premium2=0;
	switch(message){
	case "single-family":
		 premium2 =  (premium1*.005);
		break;
	case "condo":
	case "duplex":
	case "apartment":
	 premium2 =  (premium1*.006);
	 break;
	case "townhouse":
	case "rowhouse":
	premium2 =  (premium1*.007);
		break;
	}
	double premium =  (premium1+premium2);
	double mothlyPremium=premium/12;
	
	//CoverageVO  coverageVO=new CoverageVO(MonthlyPremuim, DwellingCoverage, DetachedStructorsl, PersonalProperty, Additional, Medical, deductible)
	//CoverageVO  coverageVO=new CoverageVO(mothlyPremium, dweleingCovereage, detacheStructure, personalProperty, living, ALC, deductable);
	CoverageVO coverageVO = CoverageVO.builder().MonthlyPremuim(mothlyPremium).DwellingCoverage(dweleingCovereage)
			.DetachedStructorsl(detacheStructure).PersonalProperty(personalProperty).Additional(living).Medical(ALC)
			.deductible(deductable).build();

	responseDto.setDetail(coverageVO);
	responseDto.setStatusCode(706);
	return responseDto;
	}


	private PropertyEntity getPropertyEntityByPropertyId(long propertyId) {
		final String uri = propertyServiceUrl+propertyId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Response> responseEntity = restTemplate.exchange(uri,HttpMethod.GET,getReqiuestEntity(), Response.class);
		if(responseEntity.hasBody())	{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(responseEntity.getBody().getDetail(), PropertyEntity.class);
		}
		return null;
	}


	private HttpEntity<String> getReqiuestEntity() {
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
		HttpHeaders headers = new HttpHeaders();
		headers.set(HEADER_NAME_AUTH,request.getHeader(HEADER_NAME_AUTH));
		return new HttpEntity<>(headers);
	}


	@Override
	public Response<?> saveQuote(QuoteAggregateRoot quoteAggregateRoot) throws SaveException {
		//return QuoteEntity.newFactory(quoteRepository).saveQuote(coverageEntity);
		CoverageEntity coverageEntity=quoteAggregateRoot.getCoverageEntity();
		Quote quote=new Quote();
		Response responseDto = new Response<>();
		
		quote.setAdditional(coverageEntity.getCoverageVO().getAdditional());
		quote.setDeductible(coverageEntity.getCoverageVO().getDeductible());
		quote.setDetachedStructorsl(coverageEntity.getCoverageVO().getDetachedStructorsl());
		quote.setDwellingCoverage(coverageEntity.getCoverageVO().getDwellingCoverage());
		quote.setMedical(coverageEntity.getCoverageVO().getMedical());
		quote.setMonthlyPremuim(coverageEntity.getCoverageVO().getMonthlyPremuim());
		quote.setPersonalProperty(coverageEntity.getCoverageVO().getPersonalProperty());
		quote.setPropertyId(coverageEntity.getPropertyId());
		quote.setUserId(coverageEntity.getUserId());
		
		 if(quoteRepository.save(quote)==null)
		 {
			 throw new SaveException(QuoteUtility.SAVE_ERROR);
		 }
		
		responseDto.setDetail("quote detail saved successfully");
		responseDto.setStatusCode(707);
		return responseDto;
	}


	@Override
	public Response<?> getQuoteByPropertyId(long propertyId) throws QuoteException {
		//return QuoteEntity.newFactory(quoteRepository).getQuote(propertyId);
		Optional<Quote> quote=quoteRepository.findByPropertyId(propertyId);
		
		if(!quote.isPresent())
		{
			throw new QuoteException(QuoteUtility.QUOTE_ERROR);
		}
		CoverageVO coverageVO = CoverageVO.builder().MonthlyPremuim(quote.get().getMonthlyPremuim()).DwellingCoverage(quote.get().getDwellingCoverage())
				.DetachedStructorsl(quote.get().getDetachedStructorsl()).PersonalProperty(quote.get().getPersonalProperty()).Additional(quote.get().getAdditional()).Medical(quote.get().getMedical())
				.deductible(quote.get().getDeductible()).build();

		Response responseDto = new Response<>();
		responseDto.setDetail(coverageVO);
		responseDto.setStatusCode(709);
	return responseDto;
	}


	@Override
	public Response<?> getQuoteByQuoteId(long quoteId) throws QuoteException {
Optional<Quote> quote=quoteRepository.findById(quoteId);
		
		if(!quote.isPresent())
		{
			throw new QuoteException(QuoteUtility.QUOTE_ERROR);
		}
		CoverageVO coverageVO = CoverageVO.builder().MonthlyPremuim(quote.get().getMonthlyPremuim()).DwellingCoverage(quote.get().getDwellingCoverage())
				.DetachedStructorsl(quote.get().getDetachedStructorsl()).PersonalProperty(quote.get().getPersonalProperty()).Additional(quote.get().getAdditional()).Medical(quote.get().getMedical())
				.deductible(quote.get().getDeductible()).build();

		Response responseDto = new Response<>();
		responseDto.setDetail(coverageVO);
		responseDto.setStatusCode(709);
	return responseDto;
	}

	

}
