package com.hcl.dna.homeinsurance.user;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dna.homeinsurance.user.auth.JwtTokenUtil;
import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.domain.JwtResponse;
import com.hcl.dna.homeinsurance.user.domain.LoginDto;
import com.hcl.dna.homeinsurance.user.domain.RegisterModel;
import com.hcl.dna.homeinsurance.user.service.UserService;
import com.hcl.dna.homeinsurance.user.service.UserServiceImplementation;

@RestController
@RequestMapping("api/users")
public class Controller {

	private final Logger LOGGER = Logger.getLogger(Controller.class.getName());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private UserServiceImplementation userServiceImpl;

	@PostMapping(value = "/register")
	public Long registerUser(@RequestBody RegisterModel registerModel) throws Exception {
		LOGGER.info("Inside registerUser");
		return userDetailsService.saveRegisterInfo(registerModel);
	}

	@GetMapping("/user/info")
	public UserDetails getUserDetails() {
		String username = userDetailsService.getLoggedInUser();
		LOGGER.info("Inside getUserByUsername");
		return userServiceImpl.loadUserByUsername(username);

	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> loginAuthentication(@RequestBody LoginDto loginDto) throws Exception {
		LOGGER.info("Inside login");

		authenticate(loginDto.getUsername(), loginDto.getPassword());

		final UserDetails userDetails = this.userServiceImpl.loadUserByUsername(loginDto.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PatchMapping(path = "/{username}")
	public void updatePersonalInfo(@PathVariable String username, @RequestBody HomeOwnerEntity homeOwner) {
		LOGGER.info("Inside updatePersonalInfo");
		userDetailsService.updatePersonalInformation(homeOwner);
	}

	@GetMapping("/userdetails")
	public HomeOwnerEntity getUserByUsername() {
		String userName = userDetailsService.getLoggedInUser();
		LOGGER.info("Inside getUserByUsername");
		return userDetailsService.getHomeOwnerByUsername(userName);
	}

	private void authenticate(String username, String password) throws Exception {

		LOGGER.info("Auth validation in Controller class");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
