package com.hcl.dna.homeinsurance.quote.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "QuoteEntityBuilder", toBuilder = true)
@JsonDeserialize(builder = QuoteEntity.QuoteEntityBuilder.class)
public class QuoteEntity {
	
	private Long quoteId;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class QuoteEntityBuilder {

	}

}