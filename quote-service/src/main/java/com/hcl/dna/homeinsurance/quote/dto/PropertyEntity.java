package com.hcl.homeinsurance.quote.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PropertyEntity {
	
	
	private PropertyInformationVO propertyInformationVO;

	private AddressVO addressVO;
	private Long userId;
	private Long propertyId;

	}

