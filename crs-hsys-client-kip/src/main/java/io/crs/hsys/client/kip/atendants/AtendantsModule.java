/**
 * 
 */
package io.crs.hsys.client.kip.atendants;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class AtendantsModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(AtendantsPresenter.class, AtendantsPresenter.MyView.class, AtendantsView.class,
				AtendantsPresenter.MyProxy.class);
	}
}
