package com.animesh.bank.customer.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.animesh.bank.customer.model.resource.Customer;

public class CustomerDTOToCustomerMapper {
	@Autowired
	private AddressDTOToAddressMapper mapper;

	public Customer map(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setFamilyName(customerDTO.getFamilyName());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setId(customerDTO.getId());
		customer.setMiddleName(customerDTO.getMiddleName());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		customer.setAddress(mapper.map(customerDTO.getAddress()));
		return customer;

	}
}
