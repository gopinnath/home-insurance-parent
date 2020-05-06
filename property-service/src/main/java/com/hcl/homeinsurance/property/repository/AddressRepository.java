package com.hcl.homeinsurance.property.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.hcl.homeinsurance.property.entity.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Optional<Address> findByPropertyId(Long propertyInformationId);

}