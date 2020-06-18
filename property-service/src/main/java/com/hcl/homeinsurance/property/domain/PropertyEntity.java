package com.hcl.homeinsurance.property.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "PropertyEntityBuilder", toBuilder = true)
@JsonDeserialize(builder = PropertyEntity.PropertyEntityBuilder.class)

public class PropertyEntity {

	private PropertyInformationVO propertyInformationVO;

	private AddressVO addressVO;
	private Long userId;
	private Long propertyId;
	

	@JsonPOJOBuilder(withPrefix = "")
	public static class PropertyEntityBuilder {

	}

	

}