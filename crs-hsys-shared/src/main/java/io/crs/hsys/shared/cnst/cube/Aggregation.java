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
public enum Aggregation implements Serializable {

	/**
	 * Szummáz.
	 */
	SUM,

	/**
	 * Átlagol.
	 */
	AVG,

	/**
	 * Előrejelző adatforrások esetén használatos, ahol az adott időszakra
	 * vonatkozó teljesítményt úgy állapítjuk meg, hogy a megadott időszak végén
	 * összesített értékből kivonjuk az időszak kezdetén összesített értéket. 
	 */
	CHANGE;

	public static final EnumSet<Aggregation> ALL = EnumSet.of(SUM, AVG, CHANGE);

}
