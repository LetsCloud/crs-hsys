/**
 * 
 */
package io.crs.hsys.shared.cnst;

import java.io.Serializable;

/**
 * @author robi
 *
 */
public enum OccStatus implements Serializable {
	VACANT, EARLYCI, CHECKIN, ARRIVED, INHOUSE, CHECKOUT, LATECO, DAY_USE, DEPARTED, OOO, OOS, SHOW, UNCHANGED;
}
