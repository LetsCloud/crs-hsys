/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktasktype;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class HkTaskTypeBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

//		install(new RoomEditorModule());

		bindPresenterWidget(HkTaskTypeBrowserPresenter.class, HkTaskTypeBrowserPresenter.MyView.class,
				HkTaskTypeBrowserView.class);

		install(new GinFactoryModuleBuilder().build(HkTaskTypeBrowserFactory.class));
	}
}
