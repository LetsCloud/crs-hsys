/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomResModule extends AbstractPresenterModule {

	/* (non-Javadoc)
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenterWidget(RoomResPresenter.class, RoomResPresenter.MyView.class,
				RoomResView.class);
	}

}
