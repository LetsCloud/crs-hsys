package io.crs.hsys.client.kip.roomstatus.filter;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RoomStatusFilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomStatusFilterPresenter.class, RoomStatusFilterPresenter.MyView.class,
				RoomStatusFilterView.class, RoomStatusFilterPresenter.MyProxy.class);
		
	}
}
