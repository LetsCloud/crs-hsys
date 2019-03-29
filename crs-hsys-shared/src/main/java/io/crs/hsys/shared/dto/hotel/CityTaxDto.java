/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import java.util.Date;

import io.crs.hsys.shared.cnst.CityTaxBase;
import io.crs.hsys.shared.dto.Dto;
import io.crs.hsys.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CityTaxDto implements Dto {

	/**
	 * Mettől érvényes.
	 */
	private Date validFrom;

	/**
	 * Számítás alapja.
	 */
	private CityTaxBase based;

	/**
	 * Szolgáltatgáskódja.
	 */
	private ServiceDto service;

	/**
	 * Mértéke, a számítás alapjától függően százalék vagy fiy összeg.
	 */
	private Float value;

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public CityTaxBase getBased() {
		return based;
	}

	public void setBased(CityTaxBase based) {
		this.based = based;
	}

	public ServiceDto getService() {
		return service;
	}

	public void setService(ServiceDto service) {
		this.service = service;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
