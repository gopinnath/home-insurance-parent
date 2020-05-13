/**
 * 
 */
package com.hcl.homeinsurance.quote.dto;

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
public class Response<T> {
	private T detail;
	private int statusCode;

	
	

}
