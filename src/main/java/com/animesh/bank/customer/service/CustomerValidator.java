package com.animesh.bank.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.animesh.bank.customer.constants.Constants;
import com.animesh.bank.customer.exception.BadRequestException;
import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.animesh.bank.customer.model.resource.Customer;
import com.animesh.bank.customer.repository.Repository;

public class CustomerValidator {
	@Autowired
	private AddressValidator addressValidator;
	@Autowired
	private Repository repository;
	public void validateCustomer(CustomerDTO customer) throws BadRequestException {
		validateMandatoryFields(customer);
		checkFOrDuplicateCustomer( customer);

		addressValidator.validateAddress(customer.getAddress());
	}

	public void validateMandatoryFields(CustomerDTO customer) throws BadRequestException {
		List<String> mandatoryFields = new ArrayList<>();
		if (StringUtils.isEmpty(customer.getFirstName())) {
			mandatoryFields.add(Constants.FIRST_NAME);
		}
		if (StringUtils.isEmpty(customer.getFamilyName())) {
			mandatoryFields.add(Constants.FAMILY_NAME);
		}
		if (StringUtils.isEmpty(customer.getAddress())) {
			mandatoryFields.add(Constants.ADDRESS);
		}

		if (StringUtils.isEmpty(customer.getEmailId())) {
			mandatoryFields.add(Constants.EMAIL);
		}

		if (StringUtils.isEmpty(customer.getDateOfBirth())) {
			mandatoryFields.add(Constants.BIRTH_DATE);
		}
		if (!mandatoryFields.isEmpty()) {
			throw new BadRequestException(
					Constants.MANDATORY_FIELDS + mandatoryFields.stream().collect(Collectors.joining(", ")));
		}

	}
	public void checkFOrDuplicateCustomer(CustomerDTO customer) throws BadRequestException {
		if(
		repository.findDuplicateCustomer(customer.getFirstName(), customer.getFamilyName(), customer.getDateOfBirth())!=null) {
			throw new BadRequestException(
					Constants.DUPLICATE_CUSTOMER);
		}
	}

}
