package com.hcl.dna.homeinsurance.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeOwnerRepository extends JpaRepository<HomeOwner, Long> {

	public Optional<HomeOwner> findByUsername(String username);
}