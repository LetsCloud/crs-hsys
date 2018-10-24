/**
 * 
 */
package io.crs.hsys.client.cfg.editor.room;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomEditorPresenter.class, RoomEditorPresenter.MyView.class, RoomEditorView.class,
				RoomEditorPresenter.MyProxy.class);
		bind(AvailabilityListEditor.class);
		bind(AvailabilityEditor.class);
	}
}
