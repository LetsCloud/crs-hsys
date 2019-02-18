/**
 * 
 */
package io.crs.hsys.client.core.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.core.browser.hotel.HotelBrowserModule;
import io.crs.hsys.client.core.browser.room.RoomBrowserModule;
import io.crs.hsys.client.core.browser.roomtype.RoomTypeBrowserModule;

/**
 * @author robi
 *
 */
public class HotelConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new HotelBrowserModule());
		install(new RoomTypeBrowserModule());
		install(new RoomBrowserModule());
//		install(new MarketGroupBrowserModule());

		bindPresenter(HotelConfigPresenter.class, HotelConfigPresenter.MyView.class, HotelConfigView.class,
				HotelConfigPresenter.MyProxy.class);
	}
}
