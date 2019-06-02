/**
 * 
 */
package io.crs.hsys.client.fro.roomplan;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class RoomPlanModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RoomPlanPresenter.class, RoomPlanPresenter.MyView.class, RoomPlanView.class,
				RoomPlanPresenter.MyProxy.class);
	}
}
