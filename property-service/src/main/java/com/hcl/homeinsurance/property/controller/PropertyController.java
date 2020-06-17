package com.hcl.homeinsurance.property.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.homeinsurance.domain.PropertyEntity;
import com.hcl.homeinsurance.property.dto.Response;
import com.hcl.homeinsurance.property.exception.AddressException;
import com.hcl.homeinsurance.property.exception.PropertyNotFoundException;
import com.hcl.homeinsurance.property.exception.SaveException;
import com.hcl.homeinsurance.property.exception.UpdationException;
import com.hcl.homeinsurance.property.service.PropertyService;

@RestController
@RequestMapping("api/properties")
public class PropertyController {
	
	  @Autowired PropertyService propertyService;
	
	
	@GetMapping("/")
	public ResponseEntity<Response<?>> getAllPropertyDetails(@RequestParam Optional<Long> userId) throws PropertyNotFoundException
	{
		if(userId.isPresent())	{
			return new ResponseEntity<>(propertyService.getPropertyDetailByUserId(userId.get()),HttpStatus.OK);
		}	else	{
			return new ResponseEntity<>(propertyService.getAllProperty(),HttpStatus.OK);	
		}
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<Response<?>> getPropertyDetailById(@PathVariable("propertyId") long propertyId) throws PropertyNotFoundException
	{
		return new ResponseEntity<>(propertyService.getPropertyDetailById(propertyId),HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Response<?>> registerProperty(@RequestBody PropertyEntity entity) throws SaveException
	{
		
		return new ResponseEntity<Response<?>>(propertyService.register(entity),HttpStatus.ACCEPTED);
	}
	
	@PutMapping()
	public ResponseEntity<Response<?>> updateProperty(@RequestBody PropertyEntity propertyEntity) throws PropertyNotFoundException, UpdationException, AddressException
	{
		
		return new ResponseEntity<Response<?>>(propertyService.updateProperty(propertyEntity),HttpStatus.ACCEPTED);
	}


	
}
