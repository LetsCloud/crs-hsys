/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class RoomStatusListModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomStatusListPresenter.class, RoomStatusListPresenter.MyView.class, RoomStatusListView.class,
				RoomStatusListPresenter.MyProxy.class);

		bindSingletonPresenterWidget(RoomStatusEditorPresenter.class, RoomStatusEditorPresenter.MyView.class,
				RoomStatusEditorView.class);

	}
}
