/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.filter;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;

/**
 * @author CR
 *
 */
public interface RoomStatusFilterUiHandlers extends UiHandlers {
	void doFilter(RoomStatusFilterDto filterDto);
}
