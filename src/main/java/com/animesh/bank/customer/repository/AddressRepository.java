package com.animesh.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animesh.bank.customer.model.domain.AddressDTO;

@Repository
public interface AddressRepository extends JpaRepository<AddressDTO, Long> {

}
