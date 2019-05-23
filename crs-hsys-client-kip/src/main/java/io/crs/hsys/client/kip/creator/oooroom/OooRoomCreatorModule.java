/**
 * 
 */
package io.crs.hsys.client.kip.creator.oooroom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class OooRoomCreatorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(OooRoomCreatorPresenter.class, OooRoomCreatorPresenter.MyView.class, OooRoomCreatorView.class,
				OooRoomCreatorPresenter.MyProxy.class);
	}
}
