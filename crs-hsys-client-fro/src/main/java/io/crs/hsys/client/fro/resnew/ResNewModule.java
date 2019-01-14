/**
 * 
 */
package io.crs.hsys.client.fro.resnew;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ResNewModule extends AbstractPresenterModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		bindPresenter(ResNewPresenter.class, ResNewPresenter.MyView.class, ResNewView.class,
				ResNewPresenter.MyProxy.class);
	}

}
