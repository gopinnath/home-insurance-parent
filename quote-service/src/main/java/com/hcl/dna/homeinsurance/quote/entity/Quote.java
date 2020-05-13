package com.hcl.homeinsurance.quote.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "quote")
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quote_id", unique = true, nullable = false)
	private Long quoteId;
	@Column 
	private double monthlyPremuim;
	@Column
	private double dwellingCoverage;
	@Column
	private double detachedStructorsl;
	@Column
	private double personalProperty;
	@Column
	private double additional;
	@Column
	private int  medical;
	@Column
	private double deductible;
	@Column
	private Long userId;
	@Column
	private Long propertyId;
	

}
