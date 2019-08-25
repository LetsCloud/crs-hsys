/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import io.crs.hsys.server.entity.common.Currency;

/**
 * @author robi
 *
 */
@Entity
public class RateByYear extends HotelChild {

	/**
	 * Árkód.
	 */
	@Index
	private Ref<RateCode> rateCode;

	/**
	 * Év
	 */
	@Index
	private Integer year;

	/**
	 * Valutanem.
	 */
	@Index
	private Ref<Currency> currency;

	/**
	 * 
	 */
	private List<RateByRoomType> ratesByRoomType = new ArrayList<RateByRoomType>();

	/**
	 * 
	 */
	public RateByYear() {
	}

	public Ref<RateCode> getRateCode() {
		return rateCode;
	}

	public void setRateCode(Ref<RateCode> rateCode) {
		this.rateCode = rateCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<RateByRoomType> getRatesByRoomType() {
		return ratesByRoomType;
	}

	public void setRatesByRoomType(List<RateByRoomType> ratesByRoomType) {
		this.ratesByRoomType = ratesByRoomType;
	}

}
