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

import gwt.material.design.client.ui.MaterialCollection;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;

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
	MaterialCollection collection;

	/**
	 */
	@Inject
	DesktopRoomStatusView(Binder uiBinder) {
		LOGGER.log(Level.INFO, "RoomStatusView()");
		initWidget(uiBinder.createAndBindUi(this));
		collection.add(new RoomStatusWidget("AB9001", RoomStatus.DIRTY, "DBLXL", "2-1-1-1", null, 2, 1,
				OccStatus.CHECKOUT, "", OccStatus.CHECKIN, ""));
		collection.add(new RoomStatusWidget("AB9002", RoomStatus.DIRTY, "DBLXL", "2-1-1-1", null, 2, 1,
				OccStatus.LATECO, "18:00", OccStatus.VACANT, ""));
		collection.add(new RoomStatusWidget("AB9003", RoomStatus.CLEAN, "TWINXL", "2-0-0-0", "Háká Kata", 2, 1,
				OccStatus.VACANT, "", OccStatus.EARLYCI, "10:00"));
		collection.add(new RoomStatusWidget("AB9004", RoomStatus.INSPECTED, "TWINXL", "2-2-0-0", null, 1, 2,
				OccStatus.CHECKOUT, "OUT", OccStatus.CHECKIN, "IN"));
		collection.add(new RoomStatusWidget("AB9005", RoomStatus.INSPECTED, "TWINXL", "2-2-0-0", null, 1, 2,
				OccStatus.INHOUSE, "", OccStatus.INHOUSE, ""));
		collection.add(new RoomStatusWidget("AB9006", RoomStatus.OOO, "TWINXL", "2-0-0-1", "Háká Kata", 2, 1,
				OccStatus.VACANT, "", OccStatus.VACANT, ""));
		collection.add(new RoomStatusWidget("AB9007", RoomStatus.OOS, "TWINXL", "2-0-2-0", "Partfis Piri", 2, 0,
				OccStatus.VACANT, "", OccStatus.CHECKIN, ""));
		collection.add(new RoomStatusWidget("AB9008", RoomStatus.SHOW, "TWINXL", "1-1-0-0", null, 1, 0,
				OccStatus.VACANT, "", OccStatus.VACANT, ""));
	}

	@Override
	public void doFilter(RoomStatusFilterEvent event) {
		// TODO Auto-generated method stub

	}

}
