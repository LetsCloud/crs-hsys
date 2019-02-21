/**
 * 
 */
package io.crs.hsys.client.kip.config.hk;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.browser.hktaskgroup.HkTaskGroupBrowserModule;
import io.crs.hsys.client.kip.browser.hktasktype.HkTaskTypeBrowserModule;
import io.crs.hsys.client.kip.meditor.hktaskgroup.HkTaskGroupEditorModule;

/**
 * @author robi
 *
 */
public class HkConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new HkTaskGroupBrowserModule());
		install(new HkTaskGroupEditorModule());
		install(new HkTaskTypeBrowserModule());

		bindPresenter(HkConfigPresenter.class, HkConfigPresenter.MyView.class, HkConfigView.class,
				HkConfigPresenter.MyProxy.class);
	}
}
