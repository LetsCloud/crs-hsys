/**
 * 
 */
package io.crs.hsys.client.cfg.browser.room;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import io.crs.hsys.client.cfg.editor.room.RoomEditorModule;

/**
 * @author robi
 *
 */
public class RoomBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RoomEditorModule());

		bindPresenterWidget(RoomBrowserPresenter.class, RoomBrowserPresenter.MyView.class, RoomBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RoomBrowserFactory.class));
	}
}
