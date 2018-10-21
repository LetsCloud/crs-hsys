/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import io.crs.hsys.shared.dto.BaseDto;
import io.crs.hsys.shared.dto.profile.AddressDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AccountDto extends BaseDto {

	/**
	 * Név
	 */
	private String name;

	/**
	 * Cím
	 */
	private AddressDto address = new AddressDto();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String ret = "AccountDto:{name=" + name + ", " + super.toString() + "}";
		return ret;
	}

}
