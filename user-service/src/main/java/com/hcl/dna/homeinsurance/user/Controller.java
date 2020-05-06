package com.hcl.dna.homeinsurance.user;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dna.homeinsurance.user.domain.HomeOwnerEntity;
import com.hcl.dna.homeinsurance.user.service.UserService;

@RestController
@RequestMapping("api/users")
public class Controller {

	private final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	@Autowired
	private UserService service;
			
	@GetMapping("/{username}")
	public HomeOwnerEntity getUserByUsername(@PathVariable String username)	{
		LOGGER.info("Inside getUserByUsername");
		return service.getHomeOwnerByUsername(username);
	}
	
	@PostMapping("/")
	public Long registerUser(@RequestParam String username,@RequestParam String password)	{
		LOGGER.info("Inside registerUser");
		return service.register(username, password);
	}
	
	@PostMapping("/login")
	public Boolean login(@RequestParam String username,@RequestParam String password)	{
		LOGGER.info("Inside login");
		return service.login(username, password);
	}
	
	@PatchMapping(path = "/{username}")
	public void updatePersonalInfo(@PathVariable String username,@RequestBody HomeOwnerEntity homeOwner)	{
		LOGGER.info("Inside updatePersonalInfo");
		service.updatePersonalInformation(homeOwner);
	}
}
