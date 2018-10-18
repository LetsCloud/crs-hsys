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
import io.crs.hsys.server.model.Registration;

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
		logger.debug("Account()");
	}

	/**
	 * Entitás létrehozása RegisterDto-ból
	 * 
	 * @param registerDto
	 */
	public Account(Registration registration) {
		this();
		this.name = registration.getUserName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Account:[name=" + name + "]>>" + super.toString();
	}
}
