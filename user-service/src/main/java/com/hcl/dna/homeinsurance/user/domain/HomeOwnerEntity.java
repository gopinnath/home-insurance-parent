package com.hcl.dna.homeinsurance.user.domain;

import java.util.Optional;

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
						.dateOfBirth(homeOwner.getDateOfBirth())
						.socialSecurityNumber(homeOwner.getSocialSecurityNumber()).hasRetired(retiredFlag).build();
				entity.setPersonalInformation(personalInfo);
			}

			return entity;
		}
	}

}
