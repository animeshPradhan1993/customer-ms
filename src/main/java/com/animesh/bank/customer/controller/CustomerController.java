package com.animesh.bank.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.animesh.bank.customer.constants.Params;
import com.animesh.bank.customer.exception.BadRequestException;
import com.animesh.bank.customer.exception.CustomerNotFoundException;
import com.animesh.bank.customer.mappers.CustomerDTOToCustomerMapper;
import com.animesh.bank.customer.mappers.CustomerToCustomerDTOMapper;
import com.animesh.bank.customer.model.domain.CustomerDTO;
import com.animesh.bank.customer.model.resource.Customer;
import com.animesh.bank.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@RestController
@RequestMapping("/customer/v1")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerToCustomerDTOMapper customerToCustomerDTOMapper;
	@Autowired
	private CustomerDTOToCustomerMapper customerDTOToCustomerMapper;

	@GetMapping("/customer")
	public List<Customer> getAllCustomers(Params params) {

		List<CustomerDTO> dtoList = customerService.retrieveCustomers(params);
		List<Customer> customers = new ArrayList<>();
		dtoList.forEach(cust -> customers.add(customerDTOToCustomerMapper.map(cust)));
		return customers;
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerbyId(@PathVariable(value = "id") long id)
			throws CustomerNotFoundException {

		return ResponseEntity.ok().body(customerDTOToCustomerMapper.map(customerService.retrieveCustomerByID(id)));
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws BadRequestException {
		Customer customer1 = customerDTOToCustomerMapper
				.map(customerService.createCustomer(customerToCustomerDTOMapper.map(customer)));
		
		return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);

	}

	@PatchMapping(path = "/customer/{id}", consumes = "application/json-patch+json")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody JsonPatch patch) throws CustomerNotFoundException, JsonProcessingException, JsonPatchException, BadRequestException {

		CustomerDTO customer = customerService.retrieveCustomerByID(id);

		CustomerDTO patched = customerService.patchCustomer(customer, patch);
		return ResponseEntity.ok(customerDTOToCustomerMapper.map(patched));

	}

}
