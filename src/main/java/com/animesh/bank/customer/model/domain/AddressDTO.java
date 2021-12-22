package com.animesh.bank.customer.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "address")
public class AddressDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "house_number")
	private String houseNumber;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "postal_code", nullable = false)
	private String postalCode;

	@Column(name = "province", nullable = false)
	private String province;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "address")
	private CustomerDTO customer;

	public AddressDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNo) {
		this.houseNumber = houseNo;
	}

	@Override
	public String toString() {
		return "AddressDTO [id=" + id + ", houseNumber=" + houseNumber + ", street=" + street + ", city=" + city
				+ ", postalCode=" + postalCode + ", province=" + province + ", customer=" + customer + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
