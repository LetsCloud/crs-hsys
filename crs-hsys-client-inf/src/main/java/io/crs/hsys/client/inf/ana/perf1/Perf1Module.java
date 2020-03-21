/**
 * 
 */
package io.crs.hsys.client.inf.ana.perf1;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class Perf1Module extends AbstractPresenterModule {

	@Override
	protected void configure() {
//		bind(Perf1Table.class).asEagerSingleton();
		
		bindPresenter(Perf1Presenter.class, Perf1Presenter.MyView.class, Perf1View.class, Perf1Presenter.MyProxy.class);
	}
}
