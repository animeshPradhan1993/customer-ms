package com.animesh.bank.customer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.animesh.bank.customer.model.domain.CustomerDTO;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<CustomerDTO, Long> {
	@Query(value = "SELECT * FROM CUSTOMER WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	List<CustomerDTO> findByEmailAddress(String emailAddress);

	@Query(value = "SELECT * FROM CUSTOMER WHERE FIRST_NAME = ?1", nativeQuery = true)
	List<CustomerDTO> findByFirstName(String firstName);

	@Query(value = "SELECT * FROM CUSTOMER WHERE FAMILY_NAME = ?1", nativeQuery = true)
	List<CustomerDTO> findByFamilyName(String familyName);

	@Query(value = "SELECT * FROM CUSTOMER WHERE BIRTH_DATE = ?1", nativeQuery = true)
	List<CustomerDTO> findByFirstName(Date dateOFBirth);

	@Query(value = "SELECT * FROM CUSTOMERS WHERE date_of_birth >= ?3 AND  date_of_birth <=?3 AND first_name=?1 AND family_name=?2", nativeQuery = true)
	CustomerDTO findDuplicateCustomer(String firstName, String family_Name, Date dateOFBirth);

}