/**
 * 
 */
package io.crs.hsys.client.kip.browser.guestroom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class GuestRoomBrowserModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(GuestRoomBrowserPresenter.class, GuestRoomBrowserPresenter.MyView.class, GuestRoomBrowserView.class,
				GuestRoomBrowserPresenter.MyProxy.class);

//		install(new AssignmentEditorModule());
//		install(new AssignmentWidgetModule());
	}
}
