package com.hcl.dna.homeinsurance.user.domain;

import java.util.Optional;
import java.util.logging.Logger;

import com.hcl.dna.homeinsurance.user.jpa.HomeOwner;
import com.hcl.dna.homeinsurance.user.jpa.HomeOwnerRepository;
import com.hcl.dna.homeinsurance.user.jpa.User;
import com.hcl.dna.homeinsurance.user.jpa.UserRepository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeOwnerEntity {

	private Long userId;

	private String username;

	private PersonalInfomationVO personalInformation;

	public static Factory newFactory(HomeOwnerRepository homeOwnerRepo, UserRepository userRepo) {
		return new Factory(homeOwnerRepo, userRepo);
	}

	public static class Factory {

		private final Logger LOGGER = Logger.getLogger(Factory.class.getName());

		private HomeOwnerRepository homeOwnerRepo;

		private UserRepository userRepo;

		private Factory(HomeOwnerRepository homeOwnerRepo, UserRepository userRepo) {
			this.homeOwnerRepo = homeOwnerRepo;
			this.userRepo = userRepo;
		}

		public HomeOwnerEntity buildFromDBByUserName(String username) {

			Optional<User> userObj = userRepo.findByUsername(username);

			if (!userObj.isPresent()) {
				return null;
			}

			/*
			 * if (userObj.isEmpty()) { return null; }
			 */

			Optional<HomeOwner> homeOwnerObj = homeOwnerRepo.findByUsername(username);
			HomeOwner homeOwner = null;
			if (homeOwnerObj.isPresent()) {
				homeOwner = homeOwnerObj.get();
			}

			return buildHomeOwnerEntity(userObj.get(), homeOwner);

		}

		public Long registerHomeOwner(String username, String password) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userRepo.save(user);
			return user.getUserId();
		}

		public void updatePersonalInformation(HomeOwnerEntity homeOwner) {
			PersonalInfomationVO personalInfo = homeOwner.getPersonalInformation();
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
						.dateOfBirth(homeOwner.getDateOfBirth())
						.socialSecurityNumber(homeOwner.getSocialSecurityNumber()).hasRetired(retiredFlag).build();
				entity.setPersonalInformation(personalInfo);
			}

			return entity;
		}
	}

}
