/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialTab;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;

/**
 * @author CR
 *
 */
public class MobileRoomStatusView extends ViewWithUiHandlers<RoomStatusUiHandlers>
		implements RoomStatusPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(MobileRoomStatusView.class.getName());

	interface Binder extends UiBinder<Widget, MobileRoomStatusView> {
	}

	@UiField
	MaterialTab tab;
	
	@UiField
	MaterialColumn col1, col2;

	/**
	*/
	@Inject
	MobileRoomStatusView(Binder uiBinder) {
		LOGGER.log(Level.INFO, "MobileRoomStatusView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(RoomStatusPresenter.SLOT_LIST, col1);
		bindSlot(RoomStatusPresenter.SLOT_FILTER, col2);
	}

	@Override
	public void doFilter(RoomStatusFilterEvent event) {
		LOGGER.log(Level.INFO, "doFilter()");
		tab.setTabIndex(0);
	}

}
