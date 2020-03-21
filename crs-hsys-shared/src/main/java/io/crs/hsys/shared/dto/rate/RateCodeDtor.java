/**
 * 
 */
package io.crs.hsys.shared.dto.rate;

import java.util.ArrayList;
import java.util.List;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateSubject;
import io.crs.hsys.shared.dto.common.CurrencyDtor;
import io.crs.hsys.shared.dto.hotel.HotelChildDto;

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

	private CurrencyDtor currency;

	private List<RatePriceType> priceTypes = new ArrayList<RatePriceType>();
	
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
		currency = builder.currency;
		priceTypes = builder.priceTypes;
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

	public CurrencyDtor getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDtor currency) {
		this.currency = currency;
	}
	
	public List<RatePriceType> getPriceTypes() {
		return priceTypes;
	}

	public void setPriceTypes(List<RatePriceType> priceTypes) {
		this.priceTypes = priceTypes;
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
		private CurrencyDtor currency;
		private List<RatePriceType> priceTypes = new ArrayList<RatePriceType>();

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

		public T currency(CurrencyDtor currency) {
			this.currency = currency;
			return self();
		}

		public T addPriceType(RatePriceType priceType) {
			this.priceTypes.add(priceType);
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
