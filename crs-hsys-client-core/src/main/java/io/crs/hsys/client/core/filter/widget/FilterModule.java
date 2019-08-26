/**
 * 
 */
package io.crs.hsys.client.core.filter.widget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.core.filter.accountchild.AccountChildFilterView;
import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.core.filter.hotelchild.HotelChildFilterView;
import io.crs.hsys.client.core.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.core.filter.profile.ProfileFilterView;
import io.crs.hsys.client.core.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.core.filter.room.RoomFilterView;
import io.crs.hsys.client.core.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.core.filter.roomtype.RoomTypeFilterView;
import io.crs.hsys.client.core.table.filter.FilterWidgetPresenter;
import io.crs.hsys.client.core.table.filter.FilterWidgetView;

/**
 * @author robi
 *
 */
public class FilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(FilterWidgetPresenter.class, FilterWidgetPresenter.MyView.class, FilterWidgetView.class);

		bindPresenterWidget(AccountChildFilterPresenter.class, AccountChildFilterPresenter.MyView.class,
				AccountChildFilterView.class);

		bindPresenterWidget(HotelChildFilterPresenter.class, HotelChildFilterPresenter.MyView.class,
				HotelChildFilterView.class);

		bindPresenterWidget(RoomTypeFilterPresenter.class, RoomTypeFilterPresenter.MyView.class,
				RoomTypeFilterView.class);

		bindPresenterWidget(RoomFilterPresenter.class, RoomFilterPresenter.MyView.class, RoomFilterView.class);

		bindPresenterWidget(ProfileFilterPresenter.class, ProfileFilterPresenter.MyView.class, ProfileFilterView.class);

		install(new GinFactoryModuleBuilder().build(FilterPresenterFactory.class));
	}
}
