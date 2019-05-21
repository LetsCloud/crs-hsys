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
	 * @param registration
	 */
	public Account(Registration registration) {
		this();
		this.name = registration.getAccountName();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
