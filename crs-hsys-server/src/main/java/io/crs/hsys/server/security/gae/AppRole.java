/**
 * 
 */
package io.crs.hsys.server.security.gae;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author robi
 *
 */
public enum AppRole implements GrantedAuthority {
	ADMIN(0), NEW_USER(1), USER(2);

	private int bit;

	AppRole(int bit) {
		this.bit = bit;
	}

	public String getAuthority() {
		return toString();
	}

	public int getBit() {
		return bit;
	}
	
}