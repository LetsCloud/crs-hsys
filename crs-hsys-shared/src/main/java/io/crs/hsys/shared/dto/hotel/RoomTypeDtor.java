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

	public RoomTypeDtor(Builder<?> builder) {
		super(builder);
		code = builder.code;
		name = builder.name;
	};

	public RoomTypeDtor(HotelChildDto parent) {
		this.setHotel(parent.getHotel());
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

	/**
	 * 
	 * @author robi
	 *
	 * @param <T>
	 */
	public static abstract class Builder<T extends Builder<T>> extends HotelChildDto.Builder<T> {

		private String code;
		private String name;

		public T code(String code) {
			this.code = code;
			return self();
		}

		public T name(String name) {
			this.name = name;
			return self();
		}

		public RoomTypeDtor build() {
			return new RoomTypeDtor(this);
		}
	}

	/**
	 * 
	 * @author robi
	 *
	 */
	protected static class Builder2 extends Builder<Builder2> {
		@Override
		protected Builder2 self() {
			return this;
		}
	}

	public static Builder<?> builder() {
		return new Builder2();
	}
}
