package com.animesh.bank.customer.mappers;

import com.animesh.bank.customer.model.domain.AddressDTO;
import com.animesh.bank.customer.model.resource.Address;

public class AddressDTOToAddressMapper {

	public Address map(AddressDTO addressDTO) {
		Address address = new Address();
		address.setCity(addressDTO.getCity());
		address.setHouseNumber(addressDTO.getHouseNumber());

		address.setPostalCode(addressDTO.getPostalCode());
		address.setProvince(addressDTO.getProvince());
		address.setStreet(addressDTO.getStreet());
		return address;

	}

}
