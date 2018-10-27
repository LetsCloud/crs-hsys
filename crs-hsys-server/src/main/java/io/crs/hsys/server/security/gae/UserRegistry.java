/**
 * 
 */
package io.crs.hsys.server.security.gae;

/**
 * @author robi
 *
 */
public interface UserRegistry {
	GaeUser findUser(String userId);

	void registerUser(GaeUser newUser);

	void removeUser(String userId);
}
