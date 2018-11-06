/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public interface RoomStatusEditorUiHandlers extends UiHandlers {

	void editStatus(RoomDto roomDto);

	void onCancel();
	
	void saveStatus(String roomKey, RoomStatus roomStatus);
}