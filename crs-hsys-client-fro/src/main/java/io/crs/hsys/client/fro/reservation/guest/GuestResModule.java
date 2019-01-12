/**
 * 
 */
package io.crs.hsys.client.fro.reservation.guest;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class GuestResModule extends AbstractPresenterModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenterWidget(GuestResPresenter.class, GuestResPresenter.MyView.class, GuestResView.class);
	}

}
