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
public class AccountChildDto extends BaseDto {

	private AccountDtor account;

	public AccountDtor getAccount() {
		return account;
	}

	public void setAccount(AccountDtor account) {
		this.account = account;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "AccountChildDtor:{" + "account=" + account + ", " + super.toString() + "}";
		return ret;
	}

}
