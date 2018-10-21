/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.filter.accountchild.AccountChildFilterPresenter;
import io.crs.hsys.client.cfg.filter.accountchild.AccountChildFilterView;
import io.crs.hsys.client.cfg.filter.hotelchild.HotelChildFilterPresenter;
import io.crs.hsys.client.cfg.filter.hotelchild.HotelChildFilterView;
import io.crs.hsys.client.cfg.filter.profile.ProfileFilterPresenter;
import io.crs.hsys.client.cfg.filter.profile.ProfileFilterView;
import io.crs.hsys.client.cfg.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.cfg.filter.room.RoomFilterView;
import io.crs.hsys.client.cfg.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.cfg.filter.roomtype.RoomTypeFilterView;
import io.crs.hsys.client.cfg.table.filter.FilterWidgetPresenter;
import io.crs.hsys.client.cfg.table.filter.FilterWidgetView;

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
