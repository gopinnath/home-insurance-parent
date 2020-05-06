package com.hcl.homeinsurance.property.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "home_info")

public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idhome_info", unique = true, nullable = false)
	private Long propertyInformationId;

	@NotEmpty(message = "dwellingStyle must not be empty")
	private String dwellingStyle;

	@NotEmpty(message = "numberOfFullBaths must not be empty")

	private String numberOfFullBaths;

	@NotEmpty(message = "numberOfHalfBaths must not be empty")
	private String numberOfHalfBaths;

	@NotEmpty(message = "pool must not be empty")
	private String pool;

	@NotEmpty(message = "roofType must not be empty")
	private String roofType;

	@NotEmpty(message = "squareFootage must not be empty")
	private String squareFootage;

	@NotEmpty(message = "typeGarage must not be empty")
	private String typeGarage;

	@NotEmpty(message = "valueOfHome must not be empty")
	private String valueOfHome;

	@NotEmpty(message = "yearWasBuilt must not be empty")
	private String yearWasBuilt;
	@JsonIgnore
	@Column(name = "user_id",unique = true)
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Property() {
	}

	public String getDwellingStyle() {
		return this.dwellingStyle;
	}

	public void setDwellingStyle(String dwellingStyle) {
		this.dwellingStyle = dwellingStyle;
	}

	public String getNumberOfFullBaths() {
		return this.numberOfFullBaths;
	}

	public void setNumberOfFullBaths(String numberOfFullBaths) {
		this.numberOfFullBaths = numberOfFullBaths;
	}

	public String getNumberOfHalfBaths() {
		return this.numberOfHalfBaths;
	}

	public void setNumberOfHalfBaths(String numberOfHalfBaths) {
		this.numberOfHalfBaths = numberOfHalfBaths;
	}

	public String getPool() {
		return this.pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getRoofType() {
		return this.roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public String getSquareFootage() {
		return this.squareFootage;
	}

	public void setSquareFootage(String squareFootage) {
		this.squareFootage = squareFootage;
	}

	public String getTypeGarage() {
		return this.typeGarage;
	}

	public void setTypeGarage(String typeGarage) {
		this.typeGarage = typeGarage;
	}

	public String getValueOfHome() {
		return this.valueOfHome;
	}

	public void setValueOfHome(String valueOfHome) {
		this.valueOfHome = valueOfHome;
	}

	public String getYearWasBuilt() {
		return this.yearWasBuilt;
	}

	public void setYearWasBuilt(String yearWasBuilt) {
		this.yearWasBuilt = yearWasBuilt;
	}

	public Long getPropertyInformationId() {
		return propertyInformationId;
	}

	public void setPropertyInformationId(Long propertyInformationId) {
		this.propertyInformationId = propertyInformationId;
	}

}