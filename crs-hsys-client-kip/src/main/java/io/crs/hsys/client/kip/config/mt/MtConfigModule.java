/**
 * 
 */
package io.crs.hsys.client.kip.config.mt;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class MtConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenter(MtConfigPresenter.class, MtConfigPresenter.MyView.class, MtConfigView.class,
				MtConfigPresenter.MyProxy.class);
	}
}
