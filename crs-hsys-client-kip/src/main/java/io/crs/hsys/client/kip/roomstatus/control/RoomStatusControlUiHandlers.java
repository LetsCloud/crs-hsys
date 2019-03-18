/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.control;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface RoomStatusControlUiHandlers extends UiHandlers {

	void makeDirty(String roomKey);

	void makeClean(String roomKey);

	void makeInspected(String roomKey);
	
	void navBack();

}
