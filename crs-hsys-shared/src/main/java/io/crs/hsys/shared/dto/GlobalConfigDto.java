/**
 * 
 */
package io.crs.hsys.shared.dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class GlobalConfigDto extends BaseDto {

	/**
	 * Key
	 */
	private String key;

	/**
	 * Key
	 */
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String ret = "GlobalConfigDto:{key=" + key + ", value=" + value + super.toString() + "}";
		return ret;
	}

}
