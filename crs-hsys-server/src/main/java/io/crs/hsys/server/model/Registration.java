/**
 * 
 */
package io.crs.hsys.server.model;

/**
 * @author robi
 *
 */
public class Registration {

	private String userName;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConfPassw() {
		return confPassw;
	}

	public void setConfPassw(String confPassw) {
		this.confPassw = confPassw;
	}
	
}
