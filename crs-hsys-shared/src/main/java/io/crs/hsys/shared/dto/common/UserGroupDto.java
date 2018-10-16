/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class UserGroupDto extends AccountChildDto {

	private String name;

	private List<AppUserDto> members = new ArrayList<AppUserDto>();

	public UserGroupDto() {
	}

	public UserGroupDto(AppUserDto member) {
		members.add(member);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUserDto> getMembers() {
		return members;
	}

	public void setMembers(List<AppUserDto> members) {
		this.members = members;
	}

}
