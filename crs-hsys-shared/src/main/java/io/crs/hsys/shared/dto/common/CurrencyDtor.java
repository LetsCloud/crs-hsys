/**
 * 
 */
package io.crs.hsys.shared.dto.common;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CurrencyDtor extends AccountChildDto {

	/**
	 * A valutanem egyedi azonosítója.
	 */
	private String code;

	/**
	 * A valutanem megnevezése
	 */
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
