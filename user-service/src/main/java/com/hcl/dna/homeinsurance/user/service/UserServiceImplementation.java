package com.hcl.dna.homeinsurance.user.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.jpa.HomeOwnerRepository;
import com.hcl.dna.homeinsurance.user.jpa.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	private final Logger LOGGER = Logger.getLogger(UserServiceImplementation.class.getName());
	
	@Autowired
	private HomeOwnerRepository homeOwnerRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public HomeOwnerEntity getHomeOwnerByUsername(String username) {
		LOGGER.info(" getHomeOwnerByUsername " + username);
		return HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).buildFromDBByUserName(username);
	}

	@Override
	public boolean login(String username, String password) {
		LOGGER.info(" login " + username);
		return HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).loginHomeOwner(username,password);
	}

	@Override
	public Long register(String username, String password) {
		LOGGER.info(" register " + username);
		return HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).registerHomeOwner(username,password);
	}

	@Override
	public void updatePersonalInformation(HomeOwnerEntity homeOwner) {
		LOGGER.info(" updatePersonalInformation " + homeOwner);
		HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).updatePersonalInformation(homeOwner);
	}
}
