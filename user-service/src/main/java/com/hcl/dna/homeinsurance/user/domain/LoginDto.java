package com.hcl.dna.homeinsurance.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDto {

	private String username;
	private String password;

}
