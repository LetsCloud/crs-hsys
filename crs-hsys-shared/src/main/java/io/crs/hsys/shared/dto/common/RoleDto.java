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
public class RoleDto extends AccountChildDto {

	/**
	 * Szerepkör kódja
	 */
	private String code;

	/**
	 * Szerepkör megenvezése
	 */
	private String title;

	/**
	 * Szerepkör leírása
	 */
	private String description;

	/**
	 * Jogosultságok
	 */
	private List<String> permissions = new ArrayList<String>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
