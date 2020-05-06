package com.hcl.homeinsurance.domain;

import java.util.ArrayList;

import java.util.List;


import java.util.Optional;
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PropertyEntity {
	private PropertyInformationVO propertyInformationVO;

	private AddressVO addressVO;
	private Long userId;
	private Long propertyId;

	public static Factory newFactory(PropertyRepository propertyRepository) {
		return new Factory(propertyRepository);
	}

	public static Factory newFactory(PropertyRepository propertyRepository, AddressRepository addressRepository) {
		return new Factory(propertyRepository, addressRepository);
	}

	public static Factory newFactory(AddressRepository addressRepository) {
		return new Factory(addressRepository);
	}

	public static class Factory {

		private PropertyRepository propertyRepository;
		private AddressRepository addressRepository;

		private Factory(PropertyRepository propertyRepository) {
			this.propertyRepository = propertyRepository;
		}

		private Factory(PropertyRepository propertyRepository, AddressRepository addressRepository) {
			this.propertyRepository = propertyRepository;
			this.addressRepository = addressRepository;
		}

		private Factory(AddressRepository addressRepository) {
			this.addressRepository = addressRepository;
		}

		public Response<?> registerProperty(PropertyEntity entity) throws SaveException {
			Response<String> responseDto = new Response<>();
			long propertyId = 0;

			if (entity.getPropertyInformationVO()!=null) {
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
				if (!(property1).equals(null)) {
					propertyId = property1.getPropertyInformationId();
					responseDto.setDetail(ApplicationConstants.REGISTERED_SUCCESS);
					responseDto.setStatusCode(ApplicationConstants.PROPERTY_SUCCESS_CODE);
				} else {
					responseDto.setDetail(ApplicationConstants.REGISTERED_FAILURE);
					responseDto.setStatusCode(ApplicationConstants.PROPERTY_FAILURE_CODE);
				}

			}

			if (entity.getAddressVO()!=null) {
				Address address = new Address();
				/*
				 * AddressVO addressVO=entity.getAddressVO();
				 * BeanUtils.copyProperties(addressVO, address);
				 */

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

		public Response<?> getPropertyByPropertyId(Long propertyId) throws PropertyNotFoundException {
			Optional<Property> property = propertyRepository.findById(propertyId);
			Response<PropertyEntity> responseDto = new Response<>();
			PropertyEntity propertyEntity = new PropertyEntity();
			if (!property.isPresent())
				throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);

			Optional<Address> address = addressRepository.findByPropertyId(propertyId);

			if(address.isPresent())
			{
				AddressVO addressVO=new AddressVO(address.get().getAddress(), address.get().getState(), address.get().getCity(), address.get().getZip(), address.get().getResdienceUse(), address.get().getResdienceType(), address.get().getAddressLine2());
				propertyEntity.setAddressVO(addressVO);
			}
			Property property2 = property.get();
			PropertyInformationVO propertyInformationVO = new PropertyInformationVO(property2.getDwellingStyle(),
					property2.getNumberOfFullBaths(), property2.getNumberOfHalfBaths(), property2.getPool(),
					property2.getRoofType(), property2.getSquareFootage(), property2.getTypeGarage(),
					property2.getValueOfHome(), property2.getYearWasBuilt());
			
			propertyEntity.setPropertyInformationVO(propertyInformationVO);
			propertyEntity.setPropertyId(property2.getPropertyInformationId());
			propertyEntity.setUserId(property2.getUserId());
			
			responseDto.setDetail(propertyEntity);
			responseDto.setStatusCode(701);
			return responseDto;
		}

		public Response<?> updateProperty(PropertyEntity propertyEntity)
				throws PropertyNotFoundException, UpdationException, AddressException {
			Response<String> responseDto = new Response<>();
			if(propertyEntity.getPropertyInformationVO()!=null)
			{
			Optional<Property> propertyObject = propertyRepository.findById(propertyEntity.getPropertyId());
			if (!propertyObject.isPresent())
				throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);
			PropertyInformationVO propertyInformationVO=propertyEntity.getPropertyInformationVO();
			Property property = propertyObject.get();
			property.setDwellingStyle(propertyInformationVO.getDwellingStyle());
			property.setNumberOfFullBaths(propertyInformationVO.getNumberOfFullBaths());
			property.setNumberOfHalfBaths(propertyInformationVO.getNumberOfHalfBaths());
			property.setPool(propertyInformationVO.getPool());
			property.setRoofType(propertyInformationVO.getRoofType());
			property.setSquareFootage(propertyInformationVO.getSquareFootage());
			property.setTypeGarage(propertyInformationVO.getTypeGarage());
			property.setValueOfHome(propertyInformationVO.getValueOfHome());
			property.setYearWasBuilt(propertyInformationVO.getYearWasBuilt());
			propertyRepository.save(property);
		
			responseDto.setDetail(ApplicationConstants.UPDATED_SUCCESS);
			responseDto.setStatusCode(702);
		
			
			}
	
			if(propertyEntity.getAddressVO()!=null)
			{
				Optional<Address> address = addressRepository.findByPropertyId(propertyEntity.getPropertyId());
				if (!address.isPresent()) 
					throw new AddressException(PropertyUtility.ADDRESS_ERROR);

					AddressVO addressVO=propertyEntity.getAddressVO();

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

	

	

		

		public Response<?> getAllProperty() throws PropertyNotFoundException {
			Response<List<PropertyEntity>> responseDto = new Response<>();
			List<PropertyEntity> propertyEntities= new  ArrayList<>();
			 List<Property> properties = propertyRepository.findAll();
			 
			 if(properties.isEmpty()) 
				 throw new PropertyNotFoundException(ApplicationConstants.PROPERTY_NOT_FOUND);
		
				
				for (Property property : properties) {
					
					PropertyEntity propertyEntity=new PropertyEntity();
					Optional<Address> address = addressRepository.findByPropertyId(property.getPropertyInformationId());
					PropertyInformationVO propertyInformationVO=null;

					if (!address.isPresent())
					{
						 propertyInformationVO=new PropertyInformationVO(property.getDwellingStyle(), property.getNumberOfFullBaths(), property.getNumberOfHalfBaths(), property.getPool(), property.getRoofType(), property.getSquareFootage(), property.getTypeGarage(), property.getValueOfHome(), property.getYearWasBuilt());
						
					}
					else {
						propertyInformationVO=new PropertyInformationVO(property.getDwellingStyle(), property.getNumberOfFullBaths(), property.getNumberOfHalfBaths(), property.getPool(), property.getRoofType(), property.getSquareFootage(), property.getTypeGarage(), property.getValueOfHome(), property.getYearWasBuilt());
						AddressVO addressVO=new AddressVO(address.get().getAddress(), address.get().getState(), address.get().getCity(), address.get().getZip(), address.get().getResdienceUse(), address.get().getResdienceType(), address.get().getAddressLine2());
						propertyEntity.setAddressVO(addressVO);
					}
					propertyEntity.setPropertyInformationVO(propertyInformationVO);
					propertyEntity.setPropertyId(property.getPropertyInformationId());
					propertyEntities.add(propertyEntity);
					
				}
				responseDto.setDetail(propertyEntities);
				responseDto.setStatusCode(703);
			 return responseDto;
		}

	

	}

}