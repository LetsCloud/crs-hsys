/**
 * 
 */
package io.crs.hsys.server.entity.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.BaseEntity;
import io.crs.hsys.server.entity.profile.Address;
import io.crs.hsys.shared.constans.PostalAddressLabel;
import io.crs.hsys.shared.dto.common.RegisterDto;

/**
 * @author robi
 *
 */
@Entity
public class Account extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(Account.class.getName());

	/**
	 * Név
	 */
	@Index
	private String name;

	/**
	 * Cím
	 */
	private Address address = new Address();

	/**
	 * Objectify miatt
	 */
	public Account() {
		logger.info("Account()");
	}

	/**
	 * Entitás létrehozása RegisterDto-ból
	 * 
	 * @param registerDto
	 */
	public Account(RegisterDto registerDto) {
		this();
		Address postalAddress = new Address();
		postalAddress.setPrimary(true);
		postalAddress.setLabel(PostalAddressLabel.WORK);
		postalAddress.setStreet(registerDto.getStreet());
		postalAddress.setCity(registerDto.getCity());
		postalAddress.setPostcode(registerDto.getPostcode());
		postalAddress.setCountry(registerDto.getPostcode());
		this.address = postalAddress;
		this.name = registerDto.getAccountName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		logger.info("setName()->" + name);
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		logger.info("setAddress()->" + address);
		this.address = address;
	}

	@Override
	public String toString() {
		return "Account:[name=" + name + "]>>" + super.toString();
	}
}
