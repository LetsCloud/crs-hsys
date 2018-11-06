/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

import io.crs.hsys.shared.dto.common.AccountChildDto;
import io.crs.hsys.shared.dto.common.AccountDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ProfileDtor extends AccountChildDto {

	private String name;

	private ProfileGroupDto profileGroup;

	public ProfileDtor() {
	}

	public ProfileDtor(Long id, Long accountId, String Name) {
		setId(id);
		setAccount(new AccountDtor(accountId));
		setName(Name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfileGroupDto getProfileGroup() {
		return profileGroup;
	}

	public void setProfileGroup(ProfileGroupDto profileGroup) {
		this.profileGroup = profileGroup;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "ProfileDtor:{" + "name=" + name + ", " + super.toString() + "}";
		return ret;
	}

}
