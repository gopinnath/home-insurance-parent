package com.hcl.dna.homeinsurance.user.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderClassName = "PersonalInfomationVOBuilder",toBuilder = true)
@JsonDeserialize(builder = PersonalInfomationVO.PersonalInfomationVOBuilder.class)
final public class PersonalInfomationVO {

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private String email;

	private String socialSecurityNumber;

	private Boolean hasRetired;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class PersonalInfomationVOBuilder	{
		
	}
}
