package com.hcl.dna.homeinsurance.user.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.domain.RegisterModel;

public interface UserService {

	public HomeOwnerEntity getHomeOwnerByUsername(String username);

	public Long saveRegisterInfo(RegisterModel registerModel);

	public void updatePersonalInformation(HomeOwnerEntity homeOwner);

	public String getLoggedInUser();

	public UserDetails loadUserByUsername(String username);
}

//Please add comments