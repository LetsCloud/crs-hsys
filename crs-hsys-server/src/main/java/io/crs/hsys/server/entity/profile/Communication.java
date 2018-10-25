/**
 * 
 */
package io.crs.hsys.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.constans.CommMode;
import io.crs.hsys.shared.dto.profile.CommunicationDto;

/**
 * Telefonszám entitás.
 * 
 * @author CR
 *
 */
public class Communication {

	public static CommunicationDto createDto(Communication entity) {
		CommunicationDto dto = new CommunicationDto();
		dto.setPrimary(entity.getPrimary());
		dto.setMode(entity.getMode());
		dto.setParameter(entity.getParameter());
		return dto;
	}

	public static List<CommunicationDto> createDtos(List<Communication> enities) {
		List<CommunicationDto> dtos = new ArrayList<CommunicationDto>();
		for (Communication entity : enities) {
			dtos.add(createDto(entity));
		}
		return dtos;
	}

	/**
	 * Elsődleges telefonszám.
	 */
	private Boolean primary;

	/**
	 * Telefonszám típusa: Munkahelyi, Otthoni, Mobil ...
	 */
	private CommMode mode;

	/**
	 * Emberi szemmel olvasható elérhetőség
	 */
	private String parameter;

	public Communication() {
	}

	public Communication(Boolean primary, CommMode mode, String parameter) {
		this.primary = primary;
		this.mode = mode;
		this.parameter = parameter;
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
}