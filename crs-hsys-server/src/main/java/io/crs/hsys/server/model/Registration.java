/**
 * 
 */
package io.crs.hsys.server.model;

/**
 * @author robi
 *
 */
public class Registration {

	private String accountName;

	private String email;

	private String password;

	private String confPassw;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getConfPassw() {
		return confPassw;
	}

	public void setConfPassw(String confPassw) {
		this.confPassw = confPassw;
	}
	
}
