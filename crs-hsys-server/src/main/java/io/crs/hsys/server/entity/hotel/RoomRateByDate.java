/**
 * 
 */
package io.crs.hsys.server.entity.hotel;

import java.util.HashMap;
import java.util.Map;

import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateRestrictionType;

/**
 * @author CR
 *
 */
public class RoomRateByDate {

	/**
	 * Árak.
	 */
	private Map<RatePriceType, Double> roomRates = new HashMap<RatePriceType, Double>();

	private Map<RateRestrictionType, Object> restrictions = new HashMap<RateRestrictionType, Object>();

	public RoomRateByDate() {
	}

	public RoomRateByDate(Map<RatePriceType, Double> roomRates, Map<RateRestrictionType, Object> restrictions) {
		this();
		this.roomRates = roomRates;
		this.restrictions = restrictions;
	}

	public Map<RateRestrictionType, Object> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(Map<RateRestrictionType, Object> restrictions) {
		this.restrictions = restrictions;
	}

	public Map<RatePriceType, Double> getRoomRates() {
		return roomRates;
	}

	public void setRoomRates(Map<RatePriceType, Double> roomRates) {
		this.roomRates = roomRates;
	}

	public static Builder builder() {
		return new RoomRateByDate.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder {

		private RoomRateByDate instance = new RoomRateByDate();

		public Builder() {
		}

		public RoomRateByDate build() {
			return instance;
		}

		public Builder roomRates(Map<RatePriceType, Double> roomRates) {
			instance.setRoomRates(roomRates);
			return this;
		}

		public Builder restrictions(Map<RateRestrictionType, Object> restrictions) {
			instance.setRestrictions(restrictions);
			return this;
		}
	}
}
