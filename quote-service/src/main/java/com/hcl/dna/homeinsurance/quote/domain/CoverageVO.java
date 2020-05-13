package com.hcl.homeinsurance.quote.domain;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "CoverageVOBuilder", toBuilder = true)
@JsonDeserialize(builder = CoverageVO.CoverageVOBuilder.class)
final public class CoverageVO {

	private double MonthlyPremuim;
	private double DwellingCoverage;
	private double DetachedStructorsl;
	private double PersonalProperty;
	private double Additional;
	private int  Medical;
	private double deductible;
	//private String quote_id;
	

	@JsonPOJOBuilder(withPrefix = "")
	public static class CoverageVOBuilder {

	}
}
