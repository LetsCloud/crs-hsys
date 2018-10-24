/**
 * 
 */
package io.crs.hsys.client.cfg.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.browser.hotel.HotelBrowserModule;
import io.crs.hsys.client.cfg.browser.marketgroup.MarketGroupBrowserModule;
import io.crs.hsys.client.cfg.browser.room.RoomBrowserModule;
import io.crs.hsys.client.cfg.browser.roomtype.RoomTypeBrowserModule;

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
		install(new MarketGroupBrowserModule());

		bindPresenter(HotelConfigPresenter.class, HotelConfigPresenter.MyView.class, HotelConfigView.class,
				HotelConfigPresenter.MyProxy.class);
	}
}
