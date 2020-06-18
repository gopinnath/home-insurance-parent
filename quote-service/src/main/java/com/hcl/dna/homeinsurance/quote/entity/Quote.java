package com.hcl.dna.homeinsurance.quote.entity;

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
	@Column(name = "premium")
	private double monthlyPremuim;
	@Column(name = "coverage")
	private double dwellingCoverage;
	@Column(name = "detached_structure")
	private double detachedStructorsl;
	@Column(name = "pp")
	private double personalProperty;
	@Column(name = "adle")
	private double additional;
	@Column(name = "me")
	private int  medical;
	@Column(name = "ded")
	private double deductible;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "property_id")
	private Long propertyId;
	

}
