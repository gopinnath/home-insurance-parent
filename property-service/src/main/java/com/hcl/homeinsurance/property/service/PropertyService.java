package com.hcl.homeinsurance.property.service;

import org.springframework.stereotype.Service;

import com.hcl.homeinsurance.domain.PropertyEntity;
import com.hcl.homeinsurance.property.dto.Response;
import com.hcl.homeinsurance.property.exception.AddressException;
import com.hcl.homeinsurance.property.exception.PropertyNotFoundException;
import com.hcl.homeinsurance.property.exception.SaveException;
import com.hcl.homeinsurance.property.exception.UpdationException;

@Service
public interface PropertyService {

	public Response<?> getAllProperty() throws PropertyNotFoundException;

	public Response<?> getPropertyDetailById(Long propertyId) throws PropertyNotFoundException;

	public Response<?> getPropertyDetailByUserId(Long userId) throws PropertyNotFoundException;

	public Response<?> register(PropertyEntity entity) throws SaveException;

	public Response<?> updateProperty(PropertyEntity propertyEntity)
			throws PropertyNotFoundException, UpdationException, AddressException;
}