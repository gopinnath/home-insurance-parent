package com.hcl.homeinsurance.quote.domain;

import java.util.Optional;

import com.hcl.homeinsurance.quote.dto.Response;
import com.hcl.homeinsurance.quote.entity.Quote;
import com.hcl.homeinsurance.quote.exception.QuoteException;
import com.hcl.homeinsurance.quote.exception.SaveException;
import com.hcl.homeinsurance.quote.repository.QuoteRepository;
import com.hcl.homeinsurance.quote.utility.QuoteUtility;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuoteEntity {
	
	private Long propertyId;
	

	public static Factory newFactory(QuoteRepository quoteRepository) {
		// TODO Auto-generated method stub
		return new Factory(quoteRepository);
	}

	public static class Factory {
		
		QuoteRepository  quoteRepository;

		private Factory(QuoteRepository quoteRepository) {
			this.quoteRepository = quoteRepository;
		}

		public Response<?> saveQuote(CoverageEntity coverageEntity) throws SaveException {
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

		public Response<?> getQuote(long propertyId) throws QuoteException {
			
			Optional<Quote> quote=quoteRepository.findByPropertyId(propertyId);
			
			if(!quote.isPresent())
			{
				throw new QuoteException(QuoteUtility.QUOTE_ERROR);
			}
			
			CoverageVO coverageVO=new CoverageVO(quote.get().getMonthlyPremuim(), quote.get().getDwellingCoverage(), quote.get().getDetachedStructorsl(), quote.get().getPersonalProperty(), quote.get().getAdditional(), quote.get().getMedical(), quote.get().getDeductible());
			Response responseDto = new Response<>();
			responseDto.setDetail(coverageVO);
			responseDto.setStatusCode(709);
		return responseDto;
		}
	
	}
}
