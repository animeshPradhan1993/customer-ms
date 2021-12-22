package com.animesh.bank.customer.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity()
@Table(name = "customers")

public class CustomerDTO {
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", firstName=" + firstName + ", familyName=" + familyName + ", address="
				+ address + ", dateOfBirth=" + dateOfBirth + ", emailId=" + emailId + ", middleName=" + middleName
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "address"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "family_name", nullable = false)
	private String familyName;

	public CustomerDTO() {
		super();
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	private AddressDTO address = new AddressDTO();;
	
	
	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "email_id", nullable = false)
	private String emailId;

	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@Column(name = "phone_Number", nullable = false)
	private Long phoneNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
