package com.hcl.dna.homeinsurance.quote.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "QuoteAggregateRootBuilder", toBuilder = true)
@JsonDeserialize(builder = QuoteAggregateRoot.QuoteAggregateRootBuilder.class)
public class QuoteAggregateRoot {
	
	private QuoteEntity quoteEntity;
	private CoverageEntity coverageEntity;
	
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class QuoteAggregateRootBuilder {

	}

}
