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
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;

/**
 * @author CR
 *
 */
public class DesktopRoomStatusView extends ViewWithUiHandlers<RoomStatusUiHandlers>
		implements RoomStatusPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(DesktopRoomStatusView.class.getName());

	interface Binder extends UiBinder<Widget, DesktopRoomStatusView> {
	}
	
	@UiField
	MaterialColumn col1, col2;

	/**
	 */
	@Inject
	DesktopRoomStatusView(Binder uiBinder) {
		LOGGER.log(Level.INFO, "RoomStatusView()");
		initWidget(uiBinder.createAndBindUi(this));
		
        bindSlot(RoomStatusPresenter.SLOT_LIST, col1);
        bindSlot(RoomStatusPresenter.SLOT_FILTER, col2);
	}

	@Override
	public void doFilter(RoomStatusFilterEvent event) {
		// TODO Auto-generated method stub
		
	}

}
