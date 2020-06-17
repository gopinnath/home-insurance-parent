package com.hcl.homeinsurance.property.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.homeinsurance.domain.AddressVO;
import com.hcl.homeinsurance.domain.PropertyEntity;
import com.hcl.homeinsurance.domain.PropertyInformationVO;
import com.hcl.homeinsurance.property.dto.Response;
import com.hcl.homeinsurance.property.entity.Address;
import com.hcl.homeinsurance.property.entity.Property;
import com.hcl.homeinsurance.property.exception.AddressException;
import com.hcl.homeinsurance.property.exception.PropertyNotFoundException;
import com.hcl.homeinsurance.property.exception.SaveException;
import com.hcl.homeinsurance.property.exception.UpdationException;
import com.hcl.homeinsurance.property.repository.AddressRepository;
import com.hcl.homeinsurance.property.repository.PropertyRepository;
import com.hcl.homeinsurance.property.utility.ApplicationConstants;
import com.hcl.homeinsurance.property.utility.PropertyUtility;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyRepository propertyRepository;
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Response<?> getAllProperty() throws PropertyNotFoundException {

		Response<List<PropertyEntity>> responseDto = new Response<>();
		List<PropertyEntity> propertyEntities = new ArrayList<>();
		List<Property> properties = propertyRepository.findAll();

		if (properties.isEmpty())
			throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);

		for (Property property : properties) {

			PropertyEntity propertyEntity = null;
			Optional<Address> address = addressRepository.findByPropertyId(property.getPropertyInformationId());

			PropertyInformationVO propertyInformationVO = PropertyInformationVO.builder()
					.dwellingStyle(property.getDwellingStyle()).numberOfFullBaths(property.getNumberOfFullBaths())
					.numberOfHalfBaths(property.getNumberOfHalfBaths()).pool(property.getPool())
					.roofType(property.getRoofType()).squareFootage(property.getSquareFootage())
					.typeGarage(property.getTypeGarage()).valueOfHome(property.getValueOfHome())
					.yearWasBuilt(property.getYearWasBuilt()).build();
			propertyEntity = PropertyEntity.builder().propertyInformationVO(propertyInformationVO)
					.propertyId(property.getPropertyInformationId()).build();
			if (address.isPresent()) {

				AddressVO addressVO = AddressVO.builder().address(address.get().getAddress())
						.addressLine2(address.get().getAddressLine2()).city(address.get().getCity())
						.resdienceType(address.get().getResdienceType()).resdienceUse(address.get().getResdienceUse())
						.state(address.get().getState()).zip(address.get().getZip()).build();
				propertyEntity = PropertyEntity.builder().addressVO(addressVO)
						.propertyInformationVO(propertyInformationVO).propertyId(property.getPropertyInformationId())
						.build();

			}

			propertyEntities.add(propertyEntity);

		}
		responseDto.setDetail(propertyEntities);
		responseDto.setStatusCode(703);
		return responseDto;
	}

	@Override
	public Response<?> getPropertyDetailById(Long propertyId) throws PropertyNotFoundException {
		Optional<Property> property = propertyRepository.findById(propertyId);
		Response<PropertyEntity> responseDto = new Response<>();

		if (!property.isPresent())
			throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);

		PropertyInformationVO propertyInformationVO = PropertyInformationVO.builder()
				.dwellingStyle(property.get().getDwellingStyle())
				.numberOfFullBaths(property.get().getNumberOfFullBaths())
				.numberOfHalfBaths(property.get().getNumberOfHalfBaths()).pool(property.get().getPool())
				.roofType(property.get().getRoofType()).squareFootage(property.get().getSquareFootage())
				.typeGarage(property.get().getTypeGarage()).valueOfHome(property.get().getValueOfHome())
				.yearWasBuilt(property.get().getYearWasBuilt()).build();
		PropertyEntity propertyEntity = PropertyEntity.builder().propertyInformationVO(propertyInformationVO)
				.propertyId(property.get().getPropertyInformationId()).build();
		Optional<Address> address = addressRepository.findByPropertyId(propertyId);

		if (address.isPresent()) {
			
			AddressVO addressVO = AddressVO.builder().address(address.get().getAddress())
					.addressLine2(address.get().getAddressLine2()).city(address.get().getCity())
					.resdienceType(address.get().getResdienceType()).resdienceUse(address.get().getResdienceUse())
					.state(address.get().getState()).zip(address.get().getZip()).build();
			propertyEntity = PropertyEntity.builder().propertyInformationVO(propertyInformationVO)
					.propertyId(property.get().getPropertyInformationId()).addressVO(addressVO).build();
		}

		responseDto.setDetail(propertyEntity);
		responseDto.setStatusCode(701);
		return responseDto;
	}

	@Override
	public Response<?> register(PropertyEntity entity) throws SaveException {

		Response<String> responseDto = new Response<>();
		long propertyId = 0;

		if (entity.getPropertyInformationVO() != null) {
			Property property = new Property();

			/*
			 * PropertyInformationVO vo = entity.getPropertyInformationVO();
			 * BeanUtils.copyProperties(vo, property);
			 */
			property.setDwellingStyle(entity.getPropertyInformationVO().getDwellingStyle());
			property.setNumberOfFullBaths(entity.getPropertyInformationVO().getNumberOfFullBaths());
			property.setNumberOfHalfBaths(entity.getPropertyInformationVO().getNumberOfHalfBaths());
			property.setPool(entity.getPropertyInformationVO().getPool());
			property.setRoofType(entity.getPropertyInformationVO().getRoofType());
			property.setSquareFootage(entity.getPropertyInformationVO().getSquareFootage());
			property.setTypeGarage(entity.getPropertyInformationVO().getTypeGarage());
			property.setValueOfHome(entity.getPropertyInformationVO().getValueOfHome());
			property.setYearWasBuilt(entity.getPropertyInformationVO().getYearWasBuilt());
			property.setUserId(entity.getUserId());
			Property property1 = propertyRepository.save(property);
			if (property1 != null) {
				propertyId = property1.getPropertyInformationId();
				responseDto.setDetail(ApplicationConstants.REGISTERED_SUCCESS);
				responseDto.setStatusCode(ApplicationConstants.PROPERTY_SUCCESS_CODE);
			} else {
				responseDto.setDetail(ApplicationConstants.REGISTERED_FAILURE);
				responseDto.setStatusCode(ApplicationConstants.PROPERTY_FAILURE_CODE);
			}

		}

		if (entity.getAddressVO() != null) {
			Address address = new Address();

			address.setAddress(entity.getAddressVO().getAddress());
			address.setAddressLine2(entity.getAddressVO().getAddressLine2());
			address.setCity(entity.getAddressVO().getCity());
			address.setResdienceType(entity.getAddressVO().getResdienceType());
			address.setResdienceUse(entity.getAddressVO().getResdienceUse());
			address.setState(entity.getAddressVO().getState());
			address.setZip(entity.getAddressVO().getZip());

			if (propertyId != 0)
				address.setPropertyId(propertyId);

			Address address1 = addressRepository.save(address);

			if (address1 == null) {
				throw new SaveException(PropertyUtility.SAVE_ERROR);
			}

			responseDto.setDetail("address saved Successfully");
			responseDto.setStatusCode(700);

		}
		return responseDto;
	}

	@Override
	public Response<?> updateProperty(PropertyEntity propertyEntity)
			throws PropertyNotFoundException, UpdationException, AddressException {

		Response<String> responseDto = new Response<>();
		if (propertyEntity.getPropertyInformationVO() != null) {
			Optional<Property> propertyObject = propertyRepository.findById(propertyEntity.getPropertyId());
			if (!propertyObject.isPresent())
				throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);
			PropertyInformationVO propertyInformationVO = propertyEntity.getPropertyInformationVO();
			Property property = propertyObject.get();

			if (propertyInformationVO.getDwellingStyle() != null && !propertyInformationVO.getDwellingStyle().isEmpty())
			property.setDwellingStyle(propertyInformationVO.getDwellingStyle());
			if (propertyInformationVO.getNumberOfFullBaths() != null && !propertyInformationVO.getNumberOfFullBaths().isEmpty())
			property.setNumberOfFullBaths(propertyInformationVO.getNumberOfFullBaths());
			if (propertyInformationVO.getNumberOfHalfBaths() != null && !propertyInformationVO.getNumberOfHalfBaths().isEmpty())
			property.setNumberOfHalfBaths(propertyInformationVO.getNumberOfHalfBaths());
			if (propertyInformationVO.getPool() != null && !propertyInformationVO.getPool().isEmpty())
			property.setPool(propertyInformationVO.getPool());
			if (propertyInformationVO.getRoofType() != null && !propertyInformationVO.getRoofType().isEmpty())
			property.setRoofType(propertyInformationVO.getRoofType());
			if (propertyInformationVO.getSquareFootage() != null && !propertyInformationVO.getSquareFootage().isEmpty())
			property.setSquareFootage(propertyInformationVO.getSquareFootage());
			if (propertyInformationVO.getTypeGarage() != null && !propertyInformationVO.getTypeGarage().isEmpty())
			property.setTypeGarage(propertyInformationVO.getTypeGarage());
			if (propertyInformationVO.getValueOfHome() != null && !propertyInformationVO.getValueOfHome().isEmpty())
			property.setValueOfHome(propertyInformationVO.getValueOfHome());
			if (propertyInformationVO.getYearWasBuilt() != null && !propertyInformationVO.getYearWasBuilt().isEmpty())
			property.setYearWasBuilt(propertyInformationVO.getYearWasBuilt());
			propertyRepository.save(property);

			responseDto.setDetail(ApplicationConstants.UPDATED_SUCCESS);
			responseDto.setStatusCode(702);

		}

		if (propertyEntity.getAddressVO() != null) {
			Optional<Address> address = addressRepository.findByPropertyId(propertyEntity.getPropertyId());
			if (!address.isPresent())
				throw new AddressException(PropertyUtility.ADDRESS_ERROR);

			AddressVO addressVO = propertyEntity.getAddressVO();

			if (addressVO.getAddress() != null && !addressVO.getAddress().isEmpty())
				address.get().setAddress(addressVO.getAddress());
			if (addressVO.getAddressLine2() != null && !addressVO.getAddressLine2().isEmpty())
				address.get().setAddressLine2(addressVO.getAddressLine2());

			if (addressVO.getCity() != null && !addressVO.getCity().isEmpty())
				address.get().setCity(addressVO.getCity());

			if (addressVO.getResdienceType() != null && !addressVO.getResdienceType().isEmpty())
				address.get().setResdienceType(addressVO.getResdienceType());

			if (addressVO.getResdienceUse() != null && !addressVO.getResdienceUse().isEmpty())
				address.get().setResdienceUse(addressVO.getResdienceUse());

			if (addressVO.getState() != null && !addressVO.getState().isEmpty())
				address.get().setState(addressVO.getState());

			if (addressVO.getZip() != null && !addressVO.getZip().isEmpty())
				address.get().setZip(addressVO.getZip());

			if (addressRepository.save(address.get()) == null)

				throw new UpdationException(PropertyUtility.UPDATE_ERROR);

			System.out.println("hello");

			responseDto.setDetail(ApplicationConstants.UPDATED_SUCCESS);
			responseDto.setStatusCode(701);

		}

		return responseDto;
	}

	@Override
	public Response<?> getPropertyDetailByUserId(Long userId) throws PropertyNotFoundException {
		Optional<Property> property = propertyRepository.findByUserId(userId);
		Response<PropertyEntity> responseDto = new Response<>();

		if (!property.isPresent())
			throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);

		PropertyInformationVO propertyInformationVO = PropertyInformationVO.builder()
				.dwellingStyle(property.get().getDwellingStyle())
				.numberOfFullBaths(property.get().getNumberOfFullBaths())
				.numberOfHalfBaths(property.get().getNumberOfHalfBaths()).pool(property.get().getPool())
				.roofType(property.get().getRoofType()).squareFootage(property.get().getSquareFootage())
				.typeGarage(property.get().getTypeGarage()).valueOfHome(property.get().getValueOfHome())
				.yearWasBuilt(property.get().getYearWasBuilt()).build();
		PropertyEntity propertyEntity = PropertyEntity.builder().propertyInformationVO(propertyInformationVO)
				.propertyId(property.get().getPropertyInformationId()).build();
		
		Optional<Address> address = addressRepository.findByPropertyId(property.get().getPropertyInformationId());

		if (address.isPresent()) {
			
			AddressVO addressVO = AddressVO.builder().address(address.get().getAddress())
					.addressLine2(address.get().getAddressLine2()).city(address.get().getCity())
					.resdienceType(address.get().getResdienceType()).resdienceUse(address.get().getResdienceUse())
					.state(address.get().getState()).zip(address.get().getZip()).build();
			propertyEntity = PropertyEntity.builder().propertyInformationVO(propertyInformationVO)
					.propertyId(property.get().getPropertyInformationId()).addressVO(addressVO).build();
		}

		responseDto.setDetail(propertyEntity);
		responseDto.setStatusCode(701);
		return responseDto;
	}

}
