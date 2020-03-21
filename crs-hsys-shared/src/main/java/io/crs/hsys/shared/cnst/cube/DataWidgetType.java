/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * Adatcsempe típusok
 * 
 * @author CR
 *
 */
public enum DataWidgetType implements Serializable {

	/**
	 * Csak egy mutató.
	 */
	VALUE_ONLY(Cnst.VALUE_ONLY, 1, 1),

	/**
	 * Átlagol.
	 */
	VALUE_DELTA(Cnst.VALUE_DELTA, 1, 1),

	/**
	 * Átlagol.
	 */
	VALUE_CHART(Cnst.VALUE_CHART, 1, 1),

	/**
	 * Eltérés az előzőtől
	 */
	VALUE_DELTA_CHART(Cnst.VALUE_DELTA_CHART, 1, 2),

	/**
	 * 
	 */
	VALUE_DELTA_CHART2(Cnst.VALUE_DELTA_CHART2, 2, 2),

	/**
	 * 
	 */
	MULTI_VALUE(Cnst.MULTI_VALUE, 1, 2),

	/**
	 * 
	 */
	TABLE("table", 2, 1),

	/**
	 * 
	 */
	GEO_CHART(Cnst.GEO_CHART, 2, 1);

	private String name;

	private Integer sizeX;

	private Integer sizeY;

	private DataWidgetType(String name, Integer sizeX, Integer sizeY) {
		this.name = name;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public String getName() {
		return name;
	}

	public Integer getSizeX() {
		return sizeX;
	}

	public Integer getSizeY() {
		return sizeY;
	}

	public static final EnumSet<DataWidgetType> ALL = EnumSet.of(VALUE_ONLY, VALUE_DELTA, VALUE_DELTA_CHART,
			VALUE_DELTA_CHART2, MULTI_VALUE, TABLE, GEO_CHART);

	public static class Cnst {
		public static final String VALUE_ONLY = "valueOnly";
		public static final String VALUE_DELTA = "valueDelta";
		public static final String VALUE_CHART = "valueChart";
		public static final String VALUE_DELTA_CHART = "valueDeltaChart";
		public static final String VALUE_DELTA_CHART2 = "valueDeltaChart2";
		public static final String MULTI_VALUE = "multiValue";
		public static final String GEO_CHART = "geoChart";
	}
}
