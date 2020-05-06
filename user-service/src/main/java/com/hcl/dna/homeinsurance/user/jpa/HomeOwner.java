package com.hcl.dna.homeinsurance.user.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "home_owner")
@NamedQuery(name = "HomeOwner.findAll", query = "SELECT h FROM HomeOwner h")
public class HomeOwner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "home_owner_id", unique = true, nullable = false)
	private Long homeOwnerId;

	@Column(name = "are_you_retired", length = 45)
	private String areYouRetired;

	@Column(name = "date_of_birth", length = 45)
	private Date dateOfBirth;

	@Column(length = 45)
	private String email;

	@Column(name = "first_name", length = 45)
	private String firstName;

	@Column(name = "last_name", length = 45)
	private String lastName;

	@Column(name = "social_security_number", length = 45)
	private String socialSecurityNumber;

	@Column(name = "username", length = 45)
	private String username;

	public HomeOwner() {
	}

	public String getAreYouRetired() {
		return this.areYouRetired;
	}

	public void setAreYouRetired(String areYouRetired) {
		this.areYouRetired = areYouRetired;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getHomeOwnerId() {
		return homeOwnerId;
	}

	public void setHomeOwnerId(Long homeOwnerId) {
		this.homeOwnerId = homeOwnerId;
	}

}