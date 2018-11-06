package io.crs.hsys.client.kip.roomstatus;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class MobileRoomStatusModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomStatusPresenter.class, RoomStatusPresenter.MyView.class, MobileRoomStatusView.class,
				RoomStatusPresenter.MyProxy.class);
	}
}
