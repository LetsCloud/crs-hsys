/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import io.crs.hsys.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AccountDtor extends BaseDto {

	/**
	 * Név
	 */
	private String name;

	public AccountDtor() {
	}

	public AccountDtor(Long id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String ret = "AccountDtor:{name=" + name + ", " + super.toString() + "}";
		return ret;
	}

}
