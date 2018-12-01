/**
 * 
 */
package io.crs.hsys.shared.dto.hk;

import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class GuestNumber implements Dto {

	private Integer adult;

	private Integer tiny;

	private Integer child;

	private Integer infant;

	public GuestNumber() {}

	public GuestNumber(Integer adult, Integer tiny, Integer child, Integer infant) {
		this();
		this.adult = adult; 
		this.tiny = tiny; 
		this.child = child; 
		this.infant = infant; 
	}
	
	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getTiny() {
		return tiny;
	}

	public void setTiny(Integer tiny) {
		this.tiny = tiny;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public Integer getInfant() {
		return infant;
	}

	public void setInfant(Integer infant) {
		this.infant = infant;
	}

	@Override
	public String toString() {
		return adult + "-" + tiny + "-" + child + "-" + infant;
	}
}
