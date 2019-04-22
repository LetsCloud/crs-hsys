/**
 * 
 */
package io.crs.hsys.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.cnst.UserPerm;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AppUserDtor extends AccountChildDto {

	/**
	 * Kód
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Felhasználói jogok
	 */
	private List<UserPerm> permissions = new ArrayList<UserPerm>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserPerm> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UserPerm> permissions) {
		this.permissions = permissions;
	}

	public static class Builder {

		private String code;
		private String name;
		private List<UserPerm> permissions = new ArrayList<UserPerm>();

		public Builder() {
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder permissions(List<UserPerm> permissions) {
			this.permissions = permissions;
			return this;
		}

		public AppUserDtor build() {
			AppUserDtor result = new AppUserDtor();
			result.setCode(code);
			result.setName(name);
			result.setPermissions(permissions);
			return result;
		}
	}

}
