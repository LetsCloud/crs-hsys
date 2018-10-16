/**
 * 
 */
package io.crs.hsys.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * @author robi
 *
 */
@Entity
public class Role extends AccountChild {
	private static final Logger LOGGER = LoggerFactory.getLogger(Role.class.getName());

	/**
	 * Szerepkör kódja. Indexelt, hogy egyedi kúlcsként működjön.
	 */
	@Index
	private String code;

	/**
	 * Szerepkör megnevezése
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

	public Role() {
		LOGGER.info("Role()");
	}

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
