/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CR
 *
 */
public enum Dimension implements Serializable {

	NONE("none"),

	/**
	 * GPS által meghatározott.
	 */
	GPS_SCALE("gpsScale"),

	HOTEL_CODE("hotel"),

	BSNS_DATE("bsnsDate"),

	BSNS_DAY("bsnsDay"),

	BSNS_WEEK("bsnsWeek"),

	BSNS_MONTH("bsnsMonth"),

	BSNS_QUARTER("bsnsQuarter"),

	BSNS_HALFYEAR("bsnsHalfyear"),

	BSNS_YEAR("bsnsYear"),

	MARKET("market"),

	CHANNEL("channel"),

	SOURCE("source"),

	TYPE("type"),

	COUNTRY("country"),

	CUSTOMER("customer"),

	RATE_CODE("rateCode"),

	ROOM_TYPE("roomType"),

	RES_ID("resId"),

	FCST_DATE("fcstDate"),

	FCST_WEEK("fcstWeek"),

	FCST_MONTH("fcstMonth"),

	FCST_QUARTER("fcstQuarter"),

	FCST_HALFYEAR("fcstHalfyear"),

	FCST_YEAR("fcstYear");

	private String name;

	private Dimension(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static final EnumSet<Dimension> BDGT_CAP_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE);

	public static final EnumSet<Dimension> BDGT_PFM_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE);

	public static final EnumSet<Dimension> BDGT_OCC_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE);

	public static final EnumSet<Dimension> ACTL_CAP_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, MARKET, CHANNEL, SOURCE, TYPE, COUNTRY,
			CUSTOMER, RATE_CODE);

	public static final EnumSet<Dimension> ACTL_PFM_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, MARKET, CHANNEL, SOURCE, TYPE, COUNTRY,
			CUSTOMER, RATE_CODE);

	public static final EnumSet<Dimension> ACTL_OCC_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, MARKET, CHANNEL, SOURCE, TYPE, COUNTRY,
			CUSTOMER, RATE_CODE);

	public static final EnumSet<Dimension> FCST_CAP_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, FCST_DATE, FCST_WEEK, FCST_MONTH,
			FCST_QUARTER, FCST_HALFYEAR, FCST_YEAR);

	public static final EnumSet<Dimension> FCST_PFM_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, FCST_DATE, FCST_WEEK, FCST_MONTH,
			FCST_QUARTER, FCST_HALFYEAR, FCST_YEAR);

	public static final EnumSet<Dimension> FCST_OCC_DIMS = EnumSet.of(HOTEL_CODE, BSNS_DATE, BSNS_DAY, BSNS_WEEK,
			BSNS_MONTH, BSNS_QUARTER, BSNS_HALFYEAR, BSNS_YEAR, ROOM_TYPE, FCST_DATE, FCST_WEEK, FCST_MONTH,
			FCST_QUARTER, FCST_HALFYEAR, FCST_YEAR);

	public static final Map<DataSource, EnumSet<Dimension>> DS_FRO_DIMS;
	static {
		Map<DataSource, EnumSet<Dimension>> temp = new HashMap<DataSource, EnumSet<Dimension>>();
		temp.put(DataSource.FRO_BDGT_CAPY, BDGT_CAP_DIMS);
		temp.put(DataSource.FRO_BDGT_PERF, BDGT_PFM_DIMS);
		temp.put(DataSource.FRO_BDGT_CALC, BDGT_OCC_DIMS);
		temp.put(DataSource.FRO_ACTL_CAPY, ACTL_CAP_DIMS);
		temp.put(DataSource.FRO_ACTL_PERF, ACTL_PFM_DIMS);
		temp.put(DataSource.FRO_ACTL_CALC, ACTL_OCC_DIMS);
		temp.put(DataSource.FRO_FCST_CAPY, FCST_CAP_DIMS);
		temp.put(DataSource.FRO_FCST_PERF, FCST_PFM_DIMS);
		temp.put(DataSource.FRO_FCST_CALC, FCST_OCC_DIMS);
		DS_FRO_DIMS = Collections.unmodifiableMap(temp);
	}

}
