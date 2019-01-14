/**
 * 
 */
package io.crs.hsys.client.fro.reservation.extra;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ExtraResModule extends AbstractPresenterModule {

	/* (non-Javadoc)
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenterWidget(ExtraResPresenter.class, ExtraResPresenter.MyView.class,
				ExtraResView.class);
	}

}
