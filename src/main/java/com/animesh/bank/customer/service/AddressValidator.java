package com.animesh.bank.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.animesh.bank.customer.constants.Constants;
import com.animesh.bank.customer.exception.BadRequestException;
import com.animesh.bank.customer.model.domain.AddressDTO;

public class AddressValidator {
	public void validateAddress(AddressDTO address) throws BadRequestException {
		validateAddressForMandatoryFields(address);
	}

	private void validateAddressForMandatoryFields(AddressDTO address) throws BadRequestException {
		List<String> mandatoryFields = new ArrayList<>();
		if (StringUtils.isEmpty(address.getCity())) {
			mandatoryFields.add(Constants.ADDRESS_CITY);
		}
		if (StringUtils.isEmpty(address.getPostalCode())) {
			mandatoryFields.add(Constants.ADDRESS_POSTAL_CODE);

		}
		if (StringUtils.isEmpty(address.getProvince())) {
			mandatoryFields.add(Constants.ADDRESS_PROVINCE);

		}
		if (StringUtils.isEmpty(address.getStreet())) {
			mandatoryFields.add(Constants.ADDRESS_PROVINCE);

		}

		if (!mandatoryFields.isEmpty()) {
			throw new BadRequestException(
					Constants.MANDATORY_FIELDS + mandatoryFields.stream().collect(Collectors.joining(", ")));
		}
	}
}
