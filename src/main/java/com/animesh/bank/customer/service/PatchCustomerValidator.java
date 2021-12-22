package com.animesh.bank.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.animesh.bank.customer.exception.BadRequestException;
import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.github.fge.jsonpatch.JsonPatch;

public class PatchCustomerValidator {
	@Autowired
	private AddressValidator addressValidator;
	@Autowired
	private CustomerValidator customerValidator;
	@Value("#{'${allowedPathForPatch}'.split('[,]')}")

	private List<String> patchableAttributes;

	public void validate(CustomerDTO customer) throws BadRequestException {

		customerValidator.validateMandatoryFields(customer);

		addressValidator.validateAddress(customer.getAddress());
	}

	public void validateAttribute(JsonPatch patch) throws BadRequestException {
		System.out.println(patch.toString());
		String path = patch.toString().split(";")[1].split(":")[1].replaceAll("\"", "").trim();
		System.out.println(path);
		System.out.println(patchableAttributes);
		if (!patchableAttributes.contains(path)) {
			throw new BadRequestException("requested Attribute is not allowed for update");
		}

	}

}
