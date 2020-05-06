package com.hcl.homeinsurance.property.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "address_info")

public class Address {
	@Id
	@Column(name = "property_id")
	private Long propertyId;

	@NotEmpty(message = "address must not be empty")
	@Column(name = "address", length = 45)
	private String address;
	@NotEmpty(message = "state must not be empty")
	@Column(name = "state", length = 45)
	private String state;

	@NotEmpty(message = "city must not be empty")
	@Column(name = "city", length = 45)
	private String city;
	@NotEmpty(message = "zip must not be empty")
	@Column(name = "zip", length = 45)
	private String zip;

	@NotEmpty(message = "resdience must not be empty")
	@Column(name = "resdience_use", length = 45)
	private String resdienceUse;

	@NotEmpty(message = "resdience_type must not be empty")
	@Column(name = "resdience_type", length = 45)
	private String resdienceType;

	@NotEmpty(message = "address_line_2 must not be empty")
	@Column(name = "address_line_2", length = 45)
	private String addressLine2;

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long long1) {
		this.propertyId = long1;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getResdienceUse() {
		return resdienceUse;
	}

	public void setResdienceUse(String resdienceUse) {
		this.resdienceUse = resdienceUse;
	}

	public String getResdienceType() {
		return resdienceType;
	}

	public void setResdienceType(String resdienceType) {
		this.resdienceType = resdienceType;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
}