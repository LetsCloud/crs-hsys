/**
 * 
 */
package io.crs.hsys.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * @author robi
 *
 */
@Entity
public class UserGroup extends AccountChild {

	@Index
	private String name;

	private List<AppUser> members = new ArrayList<AppUser>();

	public UserGroup() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUser> getMembers() {
		return members;
	}

	public void setMembers(List<AppUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		String ret = "UserG:roup{" + super.toString() + ", name=" + name + "}";
		return ret;
	}

}
