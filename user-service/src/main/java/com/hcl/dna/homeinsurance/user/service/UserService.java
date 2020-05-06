package com.hcl.dna.homeinsurance.user.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;

public interface UserService {
	
	public HomeOwnerEntity getHomeOwnerByUsername(String username);
	
	public String login(String username, String password);
	
	public Long register(String username, String password);
	
	public void updatePersonalInformation(HomeOwnerEntity homeOwner);
	
	public String getLoggedInUser();

	public UserDetails loadUserByUsername(String username);
}
