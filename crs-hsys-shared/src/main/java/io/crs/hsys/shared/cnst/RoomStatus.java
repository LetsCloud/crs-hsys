/**
 * 
 */
package io.crs.hsys.shared.cnst;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public enum RoomStatus implements Serializable {
	RS_DIRTY, RS_CLEAN, RS_INSPECTED, RS_OOO, RS_OOS, RS_SHOW;

	public static final RoomStatus[] cleaning = { RS_DIRTY, RS_CLEAN, RS_INSPECTED };
}
