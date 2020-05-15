/**
 * 
 */
package com.hcl.dna.homeinsurance.quote.domain;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author User1
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<PropertyEntity> {
	private PropertyEntity detail;
	private int statusCode;

	
	

}
