/**
 * 
 */
package io.crs.hsys.client.core.menu;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.shared.dto.hotel.HotelDtor;

/**
 * @author CR
 *
 */
public interface MenuUiHandlers extends UiHandlers {

	Boolean canReveal(String permissions);

	void setContentPush(MenuState menuState);
	
	void logout();
	
	void referesh();
	
	void setCurrentHotel(HotelDtor hotel);
}