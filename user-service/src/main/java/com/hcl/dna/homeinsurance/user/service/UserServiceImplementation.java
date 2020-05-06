package com.hcl.dna.homeinsurance.user.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.jpa.HomeOwnerRepository;
import com.hcl.dna.homeinsurance.user.jpa.User;
import com.hcl.dna.homeinsurance.user.jpa.UserRepository;
import com.hcl.dna.homeinsurance.user.auth.JwtTokenUtil;

@Service
public class UserServiceImplementation implements UserService,UserDetailsService { 

	private final Logger LOGGER = Logger.getLogger(UserServiceImplementation.class.getName());
	
	@Autowired
	private HomeOwnerRepository homeOwnerRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public HomeOwnerEntity getHomeOwnerByUsername(String username) {
		LOGGER.info(" getHomeOwnerByUsername " + username);
		return HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).buildFromDBByUserName(username);
	}

	@Override
	public String login(String username, String password){
		authenticate(username, password);
		final UserDetails userDetails = loadUserByUsername(username);
		return jwtTokenUtil.generateToken(userDetails);
	}

	@Override
	public Long register(String username, String password) {
		LOGGER.info(" register " + username);
		String encodedPassword = bcryptEncoder.encode(password);
		return HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).registerHomeOwner(username,encodedPassword);
	}

	@Override
	public void updatePersonalInformation(HomeOwnerEntity homeOwner) {
		LOGGER.info(" updatePersonalInformation " + homeOwner);
		HomeOwnerEntity.newFactory(homeOwnerRepo,userRepo).updatePersonalInformation(homeOwner);
	}
	
	@Override
	public String getLoggedInUser()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername().toString();
		return username;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
				user.get().getPassword(), new ArrayList<>());
	}


	private void authenticate(String username, String password){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new RuntimeException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new RuntimeException("INVALID_CREDENTIALS", e);
		}
	}
}
