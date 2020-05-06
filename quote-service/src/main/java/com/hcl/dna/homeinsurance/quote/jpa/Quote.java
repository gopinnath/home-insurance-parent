package com.hcl.dna.homeinsurance.quote.jpa;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "quote")
@NamedQuery(name = "Quote.findAll", query = "SELECT h FROM Quote h")
public class Quote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "home_owner_id", unique = true, nullable = false)
	private Long quoteId;

}