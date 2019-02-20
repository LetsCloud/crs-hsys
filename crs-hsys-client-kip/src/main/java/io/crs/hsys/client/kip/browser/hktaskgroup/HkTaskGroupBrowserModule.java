/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktaskgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class HkTaskGroupBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

//		install(new RoomTypeEditorModule());

		bindPresenterWidget(HkTaskGroupBrowserPresenter.class, HkTaskGroupBrowserPresenter.MyView.class,
				HkTaskGroupBrowserView.class);

		install(new GinFactoryModuleBuilder().build(HkTaskGroupBrowserFactory.class));
	}
}
