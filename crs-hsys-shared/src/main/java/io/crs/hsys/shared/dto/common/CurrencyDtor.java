/**
 * 
 */
package io.crs.hsys.shared.dto.common;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CurrencyDtor extends AccountChildDto implements Comparable<CurrencyDtor> {

	/**
	 * A valutanem egyedi azonosítója.
	 */
	private String code;

	/**
	 * A valutanem megnevezése
	 */
	private String description;

	public CurrencyDtor() {
	};

	public CurrencyDtor(Builder<?> builder) {
		super(builder);
		code = builder.code;
		description = builder.description;
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

	/**
	 * 
	 * @author robi
	 *
	 * @param <T>
	 */
	public static abstract class Builder<T extends Builder<T>> extends AccountChildDto.Builder<T> {

		private String code;
		private String description;

		public T code(String code) {
			this.code = code;
			return self();
		}

		public T description(String description) {
			this.description = description;
			return self();
		}

		public CurrencyDtor build() {
			return new CurrencyDtor(this);
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
	public int compareTo(CurrencyDtor other) {
		if (other == null)
			return 1;
		return code.compareTo(other.getCode());
	}
}
