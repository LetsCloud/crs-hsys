/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomStayModule extends AbstractPresenterModule {

	/* (non-Javadoc)
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenterWidget(RoomStayPresenter.class, RoomStayPresenter.MyView.class,
				RoomResView.class);
	}

}
