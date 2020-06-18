package com.hcl.homeinsurance.property.controller;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.homeinsurance.property.domain.PropertyEntity;
import com.hcl.homeinsurance.property.dto.Response;
import com.hcl.homeinsurance.property.exception.AddressException;
import com.hcl.homeinsurance.property.exception.PropertyNotFoundException;
import com.hcl.homeinsurance.property.exception.SaveException;
import com.hcl.homeinsurance.property.exception.UpdationException;
import com.hcl.homeinsurance.property.service.PropertyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("api/properties")
public class PropertyController {

	private static final Logger LOGGER = Logger.getLogger(PropertyController.class.getName());

	@Autowired
	PropertyService propertyService;

	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	@GetMapping("/")
	public ResponseEntity<Response<?>> getAllPropertyDetails(@RequestParam Optional<Long> userId)
			throws PropertyNotFoundException {
		LOGGER.info("Logged In User : " + getLoggedInUser());
		if (userId.isPresent()) {
			LOGGER.info("User Id " + userId.get());
			return new ResponseEntity<>(propertyService.getPropertyDetailByUserId(userId.get()), HttpStatus.OK);
		} else {
			LOGGER.info("User Id is empty.");
			return new ResponseEntity<>(propertyService.getAllProperty(), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	@GetMapping("/{propertyId}")
	public ResponseEntity<Response<?>> getPropertyDetailById(@PathVariable("propertyId") long propertyId)
			throws PropertyNotFoundException {
		LOGGER.info("Logged In User : " + getLoggedInUser());
		return new ResponseEntity<>(propertyService.getPropertyDetailById(propertyId), HttpStatus.OK);
	}

	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	@PostMapping()
	public ResponseEntity<Response<?>> registerProperty(@RequestBody PropertyEntity entity) throws SaveException {
		LOGGER.info("Logged In User : " + getLoggedInUser());
		return new ResponseEntity<Response<?>>(propertyService.register(entity), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })
	@PutMapping()
	public ResponseEntity<Response<?>> updateProperty(@RequestBody PropertyEntity propertyEntity)
			throws PropertyNotFoundException, UpdationException, AddressException {
		LOGGER.info("Logged In User : " + getLoggedInUser());
		return new ResponseEntity<Response<?>>(propertyService.updateProperty(propertyEntity), HttpStatus.ACCEPTED);
	}

	private String getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername().toString();
		return username;
	}
}
