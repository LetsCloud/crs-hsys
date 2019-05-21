/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
public interface OooRoomBrowserUiHandlers extends UiHandlers {
	void createItem();

	void editItem(OooRoomDto item);

	void deleteItems(List<OooRoomDto> items);
}
