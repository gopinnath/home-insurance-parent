package com.hcl.homeinsurance.domain;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "PropertyInformationVOBuilder", toBuilder = true)
@JsonDeserialize(builder = PropertyInformationVO.PropertyInformationVOBuilder.class)
public final class PropertyInformationVO {
	@NotEmpty(message = "dwellingStyle must not be empty")
	private String dwellingStyle;

	@NotEmpty(message = "numberOfFullBaths must not be empty")

	private String numberOfFullBaths;

	@NotEmpty(message = "numberOfHalfBaths must not be empty")
	private String numberOfHalfBaths;

	@NotEmpty(message = "pool must not be empty")
	private String pool;

	@NotEmpty(message = "roofType must not be empty")
	private String roofType;

	@NotEmpty(message = "squareFootage must not be empty")
	private String squareFootage;

	@NotEmpty(message = "typeGarage must not be empty")
	private String typeGarage;

	@NotEmpty(message = "valueOfHome must not be empty")
	private String valueOfHome;

	@NotEmpty(message = "yearWasBuilt must not be empty")
	private String yearWasBuilt;

	@JsonPOJOBuilder(withPrefix = "")
	public static class PropertyInformationVOBuilder {

	}
}
