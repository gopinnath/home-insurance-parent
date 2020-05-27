package com.hcl.dna.homeinsurance.quote.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "CoverageEntityBuilder", toBuilder = true)
@JsonDeserialize(builder = CoverageEntity.CoverageEntityBuilder.class)
public class CoverageEntity {
	private CoverageVO coverageVO;
	private Long userId;
	private Long propertyId;

	@JsonPOJOBuilder(withPrefix = "")
	public static class CoverageEntityBuilder {

	}
	
}