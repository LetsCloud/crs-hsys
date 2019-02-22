/**
 * 
 */
package io.crs.hsys.client.kip.config.hk;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.kip.browser.hktasktype.HkTaskTypeBrowserModule;
import io.crs.hsys.client.kip.browser.taskgroup.TaskGroupBrowserModule;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorModule;

/**
 * @author robi
 *
 */
public class HkConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new TaskGroupBrowserModule());
		install(new TaskGroupEditorModule());
		install(new HkTaskTypeBrowserModule());

		bindPresenter(HkConfigPresenter.class, HkConfigPresenter.MyView.class, HkConfigView.class,
				HkConfigPresenter.MyProxy.class);
	}
}
