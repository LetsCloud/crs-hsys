/**
 * 
 */
package io.crs.hsys.shared.dto.profile;

import io.crs.hsys.shared.constans.CommMode;
import io.crs.hsys.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CommunicationDto implements Dto {

	/**
	 * Elsődleges telefonszám.
	 */
	private Boolean primary;

	/**
	 * Telefonszám típusa: Munkahelyi, Otthoni, Mobil ...
	 */
	private CommMode mode;

	/**
	 * Emberi szemmel olvasható telefonszám
	 */
	private String parameter;

	public CommunicationDto() {
	}

	public CommunicationDto(Boolean primary, CommMode mode) {
		this();
		this.primary = primary;
		this.mode = mode;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public CommMode getMode() {
		return mode;
	}

	public void setMode(CommMode mode) {
		this.mode = mode;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "CommunicationDto:{" + super.toString() + ", primary=" + primary + ", mode=" + mode
				+ ", parameter=" + parameter;
		return ret;
	}

}
