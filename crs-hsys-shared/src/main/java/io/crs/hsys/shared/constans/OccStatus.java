/**
 * 
 */
package io.crs.hsys.shared.constans;

import java.io.Serializable;

/**
 * @author robi
 *
 */
public enum OccStatus implements Serializable {
	UNCHANGED, VACANT, EARLYCI, CHECKIN, INHOUSE, CHECKOUT, LATECO, OOO, OOS, SHOW;
}
