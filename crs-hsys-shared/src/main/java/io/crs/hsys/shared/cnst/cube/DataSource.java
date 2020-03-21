/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * @author CR
 *
 */
public enum DataSource implements Serializable {

	/**
	 * Front office kapacitás terv adatok.
	 */
	FRO_BDGT_CAPY("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office teljesítmény terv adatok.
	 */
	FRO_BDGT_PERF("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office foglaltság terv adatok.
	 */
	FRO_BDGT_CALC("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office kapacitás tény adatok.
	 */
	FRO_ACTL_CAPY("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office teljesítmény tény adatok.
	 */
	FRO_ACTL_PERF("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office foglaltság tény adatok.
	 */
	FRO_ACTL_CALC("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office kapacitás tény adatok.
	 */
	FRO_FACT_CAPY("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office teljesítmény tény adatok.
	 */
	FRO_FACT_PERF("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office foglaltság tény adatok.
	 */
	FRO_FACT_CALC("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office kapacitás előrejelzés adatok.
	 */
	FRO_FCST_CAPY("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office teljesítmény előrejelzés adatok.
	 */
	FRO_FCST_PERF("bigquery-public-data:chicago_taxi_trips"),

	/**
	 * Front office foglaltság előrejelzés adatok.
	 */
	FRO_FCST_CALC("bigquery-public-data:chicago_taxi_trips");

	private String name;

	private DataSource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static final EnumSet<DataSource> all = EnumSet.of(FRO_BDGT_CAPY, FRO_BDGT_PERF, FRO_BDGT_CALC, FRO_ACTL_CAPY,
			FRO_ACTL_PERF, FRO_ACTL_CALC, FRO_FCST_CAPY, FRO_FCST_PERF, FRO_FCST_CALC);

}
