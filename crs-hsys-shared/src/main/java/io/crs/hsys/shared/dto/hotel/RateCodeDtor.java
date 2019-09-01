/**
 * 
 */
package io.crs.hsys.shared.dto.hotel;

import io.crs.hsys.shared.cnst.RateSubject;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RateCodeDtor extends HotelChildDto implements Comparable<RateCodeDtor> {

	/**
	 * Az árkód egyedi azonosítója.
	 */
	private String code;

	/**
	 * Az árkód megnevezése.
	 */
	private String description;

	/**
	 * Az árkód típusa: Szoba, vendég.
	 */
	private RateSubject subject;

	/**
	 * 
	 */
	public RateCodeDtor() {
	}

	public RateCodeDtor(Builder<?> builder) {
		super(builder);
		code = builder.code;
		description = builder.description;
		subject = builder.subject;
	};

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

	public RateSubject getSubject() {
		return subject;
	}

	public void setSubject(RateSubject subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @author robi
	 *
	 * @param <T>
	 */
	public static abstract class Builder<T extends Builder<T>> extends HotelChildDto.Builder<T> {

		private String code;
		private String description;
		private RateSubject subject;

		public T code(String code) {
			this.code = code;
			return self();
		}

		public T description(String description) {
			this.description = description;
			return self();
		}

		public T subject(RateSubject subject) {
			this.subject = subject;
			return self();
		}

		public RateCodeDtor build() {
			return new RateCodeDtor(this);
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

	@Override
	public int compareTo(RateCodeDtor other) {
		if (other == null)
			return 1;
		return code.compareTo(other.getCode());
	}
}
