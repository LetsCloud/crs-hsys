/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class DesktopRoomStatusModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomStatusPresenter.class, RoomStatusPresenter.MyView.class, DesktopRoomStatusView.class,
				RoomStatusPresenter.MyProxy.class);
	}
}
