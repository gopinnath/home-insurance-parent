package com.hcl.dna.homeinsurance.user.service;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;

public interface UserService {
	
	public HomeOwnerEntity getHomeOwnerByUsername(String username);
	
	public boolean login(String username, String password);
	
	public Long register(String username, String password);
	
	public void updatePersonalInformation(HomeOwnerEntity homeOwner);
}
