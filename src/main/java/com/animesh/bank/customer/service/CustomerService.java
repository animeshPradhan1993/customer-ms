package com.animesh.bank.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.animesh.bank.customer.constants.Params;
import com.animesh.bank.customer.exception.BadRequestException;
import com.animesh.bank.customer.exception.CustomerNotFoundException;
import com.animesh.bank.customer.model.domain.AddressDTO;
import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.animesh.bank.customer.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public class CustomerService {
	@Autowired
	private Repository repository;
	@Autowired
	private CustomerValidator customerValidator;
	@Autowired
	private PatchCustomerValidator patchCustomerValidator;

	public static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
	}

	public List<CustomerDTO> retrieveCustomers(Params params) {
		List<CustomerDTO> customers = (List<CustomerDTO>) repository.findAll();
		Stream<CustomerDTO> stream = customers.stream();
		if (params.getFirstName() != null) {
			stream = stream.filter(cust -> cust.getFirstName().equals(params.getFirstName()));
		}
		if (params.getFamilyName() != null) {
			stream = stream.filter(cust -> cust.getFamilyName().equals(params.getFamilyName()));
		}
		if (params.getEmailId() != null) {
			stream = stream.filter(cust -> cust.getEmailId().equals(params.getEmailId()));
		}
		return stream.collect(Collectors.toList());

	}

	public CustomerDTO retrieveCustomerByID(long id) throws CustomerNotFoundException {
		Optional<CustomerDTO> customer = repository.findById(id);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found on : " + id);
		}

		return customer.get();

	}

	public CustomerDTO createCustomer(CustomerDTO customer) throws BadRequestException {
		customerValidator.validateCustomer(customer);
		CustomerDTO dto = repository.save(customer);

		return dto;
	}

	public CustomerDTO patchCustomer(CustomerDTO customer, JsonPatch patch)
			throws JsonProcessingException, JsonPatchException, BadRequestException {
		patchCustomerValidator.validateAttribute(patch);
		CustomerDTO customerDTO = applyPatchToCustomer(patch, customer);
		AddressDTO address = customerDTO.getAddress();
		patchCustomerValidator.validate(customer);
		CustomerDTO retrieved = repository.save(customerDTO);
		retrieved.setAddress(address);
		return repository.save(customerDTO);

	}

	private CustomerDTO applyPatchToCustomer(JsonPatch patch, CustomerDTO targetCustomer)
			throws JsonPatchException, JsonProcessingException {
		JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
		return objectMapper.treeToValue(patched, CustomerDTO.class);

	}

}
