package com.hcl.dna.homeinsurance.user.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.domain.PersonalInfomationVO;
import com.hcl.dna.homeinsurance.user.domain.RegisterModel;
import com.hcl.dna.homeinsurance.user.jpa.HomeOwner;
import com.hcl.dna.homeinsurance.user.jpa.HomeOwnerRepository;
import com.hcl.dna.homeinsurance.user.jpa.User;
import com.hcl.dna.homeinsurance.user.jpa.UserRepository;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private HomeOwnerRepository homeOwnerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), new ArrayList<>());
	}

	@Override
	public Long saveRegisterInfo(RegisterModel registerModel) {
		User user = new User();
		user.setUsername(registerModel.getUsername());
		user.setPassword(bcryptEncoder.encode(registerModel.getPassword()));

		userRepo.save(user);

		return user.getUserId();
	}

	@Override
	public String getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername().toString();
		return username;
	}

	@Override
	public void updatePersonalInformation(HomeOwnerEntity homeOwner) {
		PersonalInfomationVO personalInfo = homeOwner.getPersonalInformation();
		Optional<HomeOwner> homeOwnerObj = homeOwnerRepo.findByUsername(homeOwner.getUsername());
		if(homeOwnerObj.isPresent())	{
			HomeOwner dbHomeOwner = homeOwnerObj.get();
			dbHomeOwner.setFirstName(personalInfo.getFirstName());
			dbHomeOwner.setLastName(personalInfo.getLastName());
			dbHomeOwner.setEmail(personalInfo.getEmail());
			dbHomeOwner.setDateOfBirth(personalInfo.getDateOfBirth());
			dbHomeOwner.setSocialSecurityNumber(personalInfo.getSocialSecurityNumber());
			dbHomeOwner.setAreYouRetired(personalInfo.getHasRetired() ? "Y" : "N");
			homeOwnerRepo.save(dbHomeOwner);
		}	else	{
			HomeOwner dbHomeOwner = new HomeOwner();
			dbHomeOwner.setUsername(homeOwner.getUsername());
			dbHomeOwner.setFirstName(personalInfo.getFirstName());
			dbHomeOwner.setLastName(personalInfo.getLastName());
			dbHomeOwner.setEmail(personalInfo.getEmail());
			dbHomeOwner.setDateOfBirth(personalInfo.getDateOfBirth());
			dbHomeOwner.setSocialSecurityNumber(personalInfo.getSocialSecurityNumber());
			dbHomeOwner.setAreYouRetired(personalInfo.getHasRetired() ? "Y" : "N");
			homeOwnerRepo.save(dbHomeOwner);
		}
	}

	@Override
	public HomeOwnerEntity getHomeOwnerByUsername(String username) {
		Optional<User> userObj = userRepo.findByUsername(username);

		if (!userObj.isPresent()) {
			return null;
		}
		/*
		 * if(userObj.isEmpty()) { return null; }
		 */

		Optional<HomeOwner> homeOwnerObj = homeOwnerRepo.findByUsername(username);
		HomeOwner homeOwner = null;
		if (homeOwnerObj.isPresent()) {
			homeOwner = homeOwnerObj.get();
		}

		return buildHomeOwnerEntity(userObj.get(), homeOwner);
	}

	private HomeOwnerEntity buildHomeOwnerEntity(User user, HomeOwner homeOwner) {
		HomeOwnerEntity entity = new HomeOwnerEntity();
		entity.setUsername(user.getUsername());
		entity.setUserId(user.getUserId());

		if (homeOwner != null) {
			Boolean retiredFlag = false;
			if (homeOwner.getAreYouRetired() != null && "Y".equals(homeOwner.getAreYouRetired())) {
				retiredFlag = true;
			}
			PersonalInfomationVO personalInfo = PersonalInfomationVO.builder().firstName(homeOwner.getFirstName())
					.lastName(homeOwner.getLastName()).email(homeOwner.getEmail())
					.dateOfBirth(homeOwner.getDateOfBirth()).socialSecurityNumber(homeOwner.getSocialSecurityNumber())
					.hasRetired(retiredFlag).build();
			entity.setPersonalInformation(personalInfo);
		}

		return entity;
	}

}
