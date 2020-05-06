package com.hcl.homeinsurance.property.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.homeinsurance.domain.PropertyEntity;
import com.hcl.homeinsurance.property.dto.Response;
import com.hcl.homeinsurance.property.exception.AddressException;
import com.hcl.homeinsurance.property.exception.PropertyNotFoundException;
import com.hcl.homeinsurance.property.exception.SaveException;
import com.hcl.homeinsurance.property.exception.UpdationException;
import com.hcl.homeinsurance.property.repository.AddressRepository;
import com.hcl.homeinsurance.property.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyRepository propertyRepository;
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Response<?> getAllProperty() throws PropertyNotFoundException {

		return PropertyEntity.newFactory(propertyRepository, addressRepository).getAllProperty();
	}

	@Override
	public Response<?> getPropertyDetailById(Long propertyId) throws PropertyNotFoundException {

		return PropertyEntity.newFactory(propertyRepository, addressRepository).getPropertyByPropertyId(propertyId);

	}

	@Override
	public Response<?> register(PropertyEntity entity) throws SaveException {

		return PropertyEntity.newFactory(propertyRepository, addressRepository).registerProperty(entity);
	}

	@Override
	public Response<?> updateProperty(PropertyEntity propertyEntity)
			throws PropertyNotFoundException, UpdationException, AddressException {

		return PropertyEntity.newFactory(propertyRepository, addressRepository).updateProperty(propertyEntity);
	}

}
