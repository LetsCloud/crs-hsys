/**
 * 
 */
package io.crs.hsys.client.kip.config.hk;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.browser.hktasktype.HkTaskTypeBrowserModule;

/**
 * @author robi
 *
 */
public class HkConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new HkTaskTypeBrowserModule());

		bindPresenter(HkConfigPresenter.class, HkConfigPresenter.MyView.class, HkConfigView.class,
				HkConfigPresenter.MyProxy.class);
	}
}
