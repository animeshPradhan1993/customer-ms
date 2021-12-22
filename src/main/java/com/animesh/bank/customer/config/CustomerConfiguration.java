package com.animesh.bank.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.animesh.bank.customer.exception.ExceptionIntercepter;
import com.animesh.bank.customer.mappers.AddressDTOToAddressMapper;
import com.animesh.bank.customer.mappers.AddressToAddressDTOMapper;
import com.animesh.bank.customer.mappers.CustomerDTOToCustomerMapper;
import com.animesh.bank.customer.mappers.CustomerToCustomerDTOMapper;
import com.animesh.bank.customer.service.AddressValidator;
import com.animesh.bank.customer.service.CustomerService;
import com.animesh.bank.customer.service.CustomerValidator;
import com.animesh.bank.customer.service.PatchCustomerValidator;

@Configuration
public class CustomerConfiguration {

	@Bean
	public CustomerValidator customerValidator() {
		return new CustomerValidator();
	}

	@Bean
	public AddressValidator addressValidator() {
		return new AddressValidator();
	}

	@Bean
	public CustomerToCustomerDTOMapper customerToCustomerDTOMapper() {
		return new CustomerToCustomerDTOMapper();
	}

	@Bean
	public CustomerDTOToCustomerMapper customerDTOToCustomerMapper() {
		return new CustomerDTOToCustomerMapper();
	}

	@Bean
	public AddressDTOToAddressMapper addressDTOToAddressMapper() {
		return new AddressDTOToAddressMapper();
	}

	@Bean
	public AddressToAddressDTOMapper addressToAddressDTOMapper() {
		return new AddressToAddressDTOMapper();
	}

	@Bean
	public ExceptionIntercepter exceptionIntercepter() {
		return new ExceptionIntercepter();
	}

	@Bean
	public PatchCustomerValidator patchCustomerValidator() {
		return new PatchCustomerValidator();
	}

	@Bean
	public CustomerService customerService() {
		return new CustomerService();
	}

}
