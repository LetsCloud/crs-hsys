/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import io.crs.hsys.client.cfg.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.cfg.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.cfg.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.cfg.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.cfg.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.cfg.table.filter.FilterWidgetPresenter;

/**
 * @author robi
 *
 */
public interface FilterPresenterFactory {

	AccountChildFilterPresenter createAccountChildFilter();

	HotelChildFilterPresenter createHotelChildFilter();

	FilterWidgetPresenter createFilterWidgetPresenter();

	RoomTypeFilterPresenter createRoomTypeFilterPresenter();

	RoomFilterPresenter createRoomFilterPresenter();

	ProfileFilterPresenter createProfileFilterPresenter();

}
