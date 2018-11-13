/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RoomTypeDtor extends HotelChildDto {

	private String code;

	private String name;

	public RoomTypeDtor() {
	};

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

	@Override
	public String toString() {
		return "RoomTypeDtor:{code=" + code + ", name=" + name + ", " + super.toString() + "}";
	}

	public static class Builder {

		private String code;
		private String name;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public RoomTypeDtor build() {
			RoomTypeDtor dto = new RoomTypeDtor();
			dto.setCode(code);
			dto.setName(name);
			return dto;
		}
	}

}
