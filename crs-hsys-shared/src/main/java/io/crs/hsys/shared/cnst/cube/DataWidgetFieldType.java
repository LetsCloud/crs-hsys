/**
 * 
 */
package io.crs.hsys.shared.cnst.cube;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public enum DataWidgetFieldType implements Serializable {

	/**
	 * A mutató értéke.
	 */
	VALUE(0),

	/**
	 * További mutatók értéke.
	 */
	VALUE2(1), VALUE3(2), VALUE4(3), VALUE5(4), VALUE6(5), VALUE7(6), VALUE8(7),

	/**
	 * Tény és bázis közötti különbség.
	 */
	DELTA(1),

	/**
	 * Tény adatsor.
	 */
	ACTUAL_SERIES(2),

	/**
	 * Bázis adatsor.
	 */
	BASE_SERIES(3),

	/**
	 * A mutató értéke.
	 */
	X_AXIS(4);

	private Integer index;

	private DataWidgetFieldType(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}
}
