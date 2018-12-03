/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.roomstatus.controll.RoomStatusControllModule;

/**
 * @author CR
 *
 */
public class RoomStatusModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomStatusPresenter.class, RoomStatusPresenter.MyView.class, RoomStatusView.class,
				RoomStatusPresenter.MyProxy.class);

		install(new RoomStatusControllModule());
	}
}
