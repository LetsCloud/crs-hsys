/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class MainResModule extends AbstractPresenterModule {

	/* (non-Javadoc)
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenterWidget(MainResPresenter.class, MainResPresenter.MyView.class,
				MainResView.class);
	}

}
