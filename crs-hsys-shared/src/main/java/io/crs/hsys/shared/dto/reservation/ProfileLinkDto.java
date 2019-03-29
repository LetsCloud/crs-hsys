/**
 * 
 */
package io.crs.hsys.shared.dto.reservation;

import io.crs.hsys.shared.cnst.ProfileType;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.profile.OrganizationDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ProfileLinkDto implements Dto {

	private ProfileType type;

	private OrganizationDto organization;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
}
