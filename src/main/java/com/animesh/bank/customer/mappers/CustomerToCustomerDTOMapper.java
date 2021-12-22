package com.animesh.bank.customer.mappers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.animesh.bank.customer.model.resource.Customer;

public class CustomerToCustomerDTOMapper  {
	@Autowired
	private AddressToAddressDTOMapper mapper;
	
	public CustomerDTO map(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setDateOfBirth(truncateDate(customer.getDateOfBirth()));
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setFamilyName(customer.getFamilyName());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setId(customer.getId());
		customerDTO.setMiddleName(customer.getMiddleName());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());
		customerDTO.setAddress(mapper.map(customer.getAddress()));
		return customerDTO;

	}
	public Date truncateDate(Date dateObject) {
	Calendar cal = Calendar.getInstance(); // locale-
	cal.setTime(dateObject);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	//long time = cal.getTimeInMillis();
	return cal.getTime();
	}
}
