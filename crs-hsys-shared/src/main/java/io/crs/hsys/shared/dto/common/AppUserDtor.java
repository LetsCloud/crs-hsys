/**
 * 
 */
package io.crs.hsys.shared.dto.common;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AppUserDtor extends AccountChildDto {

	/**
	 * Kód
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class Builder {

		private String code;
		private String name;

		public Builder() {
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public AppUserDtor build() {
			AppUserDtor result = new AppUserDtor();
			result.setCode(code);
			result.setName(name);
			return result;
		}
	}

}
