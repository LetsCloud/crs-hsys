/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public interface RoomStatusListUiHandlers extends UiHandlers {
	
	void editStatus(RoomDto roomDto);

}
