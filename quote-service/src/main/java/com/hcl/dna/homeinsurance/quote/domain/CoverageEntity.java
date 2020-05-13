package com.hcl.homeinsurance.quote.domain;

import java.util.Calendar;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.homeinsurance.quote.dto.PropertyEntity;
import com.hcl.homeinsurance.quote.dto.Response;
import com.hcl.homeinsurance.quote.repository.QuoteRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CoverageEntity {
	private CoverageVO coverageVO;
	private Long userId;
	private Long propertyId;

	public static Factory newFactory(QuoteRepository quoteRepository) 
	{
		return new Factory(quoteRepository);
	}

	public static class Factory {

		private QuoteRepository quoteRepository;


		private Factory(QuoteRepository quoteRepository) {
			this.quoteRepository = quoteRepository;
			
			
		}


		public Response<?> getPropertyByPropertyId(long propertyId) {
			
			Response responseDto = new Response<>();
			
			final String uri = "http://localhost:8080/api/properties/"+propertyId;
			
			RestTemplate restTemplate = new RestTemplate();

			Response<PropertyEntity> res = restTemplate.getForObject(uri, Response.class);
		
			ObjectMapper mapper = new ObjectMapper();
			PropertyEntity currentProperty =  mapper.convertValue(res.getDetail(), PropertyEntity.class);
			
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
		CoverageVO  coverageVO=new CoverageVO(mothlyPremium, dweleingCovereage, detacheStructure, personalProperty, living, ALC, deductable);
		
		responseDto.setDetail(coverageVO);
		responseDto.setStatusCode(706);
		return responseDto;
		}
	}
}