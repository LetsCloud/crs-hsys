/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.control;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomStatusControlModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RoomStatusControlPresenter.class, RoomStatusControlPresenter.MyView.class,
				RoomStatusControlView.class, RoomStatusControlPresenter.MyProxy.class);
	}
}
