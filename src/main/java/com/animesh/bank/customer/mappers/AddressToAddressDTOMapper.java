package com.animesh.bank.customer.mappers;

import com.animesh.bank.customer.model.domain.AddressDTO;
import com.animesh.bank.customer.model.resource.Address;

public class AddressToAddressDTOMapper {

	public AddressDTO map(Address address) {
		AddressDTO addressDTO= new AddressDTO();
		addressDTO.setCity(address.getCity());
		addressDTO.setHouseNumber(address.getHouseNumber());
		
		addressDTO.setPostalCode(address.getPostalCode());
		addressDTO.setProvince(address.getProvince());
		addressDTO.setStreet(address.getStreet());
		return addressDTO;
		
	}
}
