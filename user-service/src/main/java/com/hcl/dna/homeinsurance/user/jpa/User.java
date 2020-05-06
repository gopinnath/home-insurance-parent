package com.hcl.dna.homeinsurance.user.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the username database table.
 * 
 */
@Entity
@Table(name = "username")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	private long userId;

	@Column(length = 45)
	private String password;

	@Column(name = "user_role")
	private long userRole;

	@Column(length = 45)
	private String username;

	public User() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserRole() {
		return this.userRole;
	}

	public void setUserRole(long userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}