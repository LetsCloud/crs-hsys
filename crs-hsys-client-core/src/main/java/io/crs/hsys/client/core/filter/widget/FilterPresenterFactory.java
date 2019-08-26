/**
 * 
 */
package io.crs.hsys.client.core.filter.widget;

import io.crs.hsys.client.core.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.core.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.core.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.core.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.core.table.filter.FilterWidgetPresenter;

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
