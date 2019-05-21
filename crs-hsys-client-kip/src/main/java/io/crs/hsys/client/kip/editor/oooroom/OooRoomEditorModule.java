/**
 * 
 */
package io.crs.hsys.client.kip.editor.oooroom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class OooRoomEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(OooRoomEditorPresenter.class, OooRoomEditorPresenter.MyView.class, OooRoomEditorView.class,
				OooRoomEditorPresenter.MyProxy.class);
	}
}
