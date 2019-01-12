/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import java.io.Serializable;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class Customer implements Serializable {

	private String name;

	public Customer() {
	}

	public Customer(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
