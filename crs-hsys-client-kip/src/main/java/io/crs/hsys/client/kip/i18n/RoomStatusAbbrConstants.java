/**
 * 
 */
package io.crs.hsys.client.kip.i18n;

import java.util.Map;

import com.google.gwt.i18n.client.Constants;

/**
 * @author CR
 *
 */
public interface RoomStatusAbbrConstants extends Constants {

	@DefaultStringMapValue({ "CLEAN", "Cl", "DIRTY", "Dt", "INSPECTED", "Ok" })
	public Map<String, String> roomStatusAbbrMap();
}
