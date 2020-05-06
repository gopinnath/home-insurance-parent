package com.hcl.homeinsurance.domain;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;



import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "AddressVOBuilder", toBuilder = true)
@JsonDeserialize(builder = AddressVO.AddressVOBuilder.class)
final public class AddressVO {

	private String address;
	private String state;
	private String city;
	private String zip;
	private String resdienceUse;
	private String resdienceType;
	private String addressLine2;

	

	@JsonPOJOBuilder(withPrefix = "")
	public static class AddressVOBuilder {

	}
}
