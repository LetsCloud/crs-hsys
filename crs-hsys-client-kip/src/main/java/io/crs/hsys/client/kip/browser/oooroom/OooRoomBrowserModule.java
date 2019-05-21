/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class OooRoomBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(OooRoomBrowserPresenter.class, OooRoomBrowserPresenter.MyView.class, OooRoomBrowserView.class,
				OooRoomBrowserPresenter.MyProxy.class);
	}
}
