/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCollection;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent.RoomStatusEditHandler;
import io.crs.hsys.client.kip.task.editor.TaskEditorView;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;

/**
 * @author CR
 *
 */
public class RoomStatusView extends ViewWithUiHandlers<RoomStatusUiHandlers>
		implements RoomStatusPresenter.MyView {
	private static final Logger logger = Logger.getLogger(RoomStatusView.class.getName());

	interface Binder extends UiBinder<Widget, RoomStatusView> {
	}

	@UiField
	SimplePanel searchPanel, filterPanel, controllPanel;

	@UiField
	MaterialCollection collection;

	private final KipMessages i18n;
	/**
	 */
	@Inject
	RoomStatusView(Binder uiBinder, KipMessages i18n) {
		logger.log(Level.INFO, "RoomStatusView()");
		this.i18n = i18n;
		initWidget(uiBinder.createAndBindUi(this));
		
		bindSlot(RoomStatusPresenter.SEARCH_SLOT, searchPanel);
		bindSlot(RoomStatusPresenter.FILTER_SLOT, filterPanel);
		bindSlot(RoomStatusPresenter.EDITOR_SLOT, controllPanel);
		
		/*
		collection.add(new RoomStatusWidget("AB9001", RoomStatus.DIRTY, "DBLXL", "2-1-1-1", null, 2, 1,
				OccStatus.CHECKOUT, "", OccStatus.CHECKIN, ""));
		collection.add(new RoomStatusWidget("AB9002", RoomStatus.DIRTY, "DBLXL", "2-1-1-1", null, 2, 1,
				OccStatus.LATECO, "18:00", OccStatus.VACANT, "", true));
		collection.add(new RoomStatusWidget("AB9003", RoomStatus.CLEAN, "TWINXL", "2-0-0-0", "Háká Kata", 2, 1,
				OccStatus.VACANT, "", OccStatus.EARLYCI, "10:00"));
		collection.add(new RoomStatusWidget("AB9004", RoomStatus.INSPECTED, "TWINXL", "2-2-0-0", null, 1, 2,
				OccStatus.CHECKOUT, "OUT", OccStatus.CHECKIN, "IN", true));
		collection.add(new RoomStatusWidget("AB9005", RoomStatus.INSPECTED, "TWINXL", "2-2-0-0", null, 1, 2,
				OccStatus.INHOUSE, "", OccStatus.INHOUSE, ""));
		collection.add(new RoomStatusWidget("AB9006", RoomStatus.OOO, "TWINXL", "2-0-0-1", "Háká Kata", 2, 1,
				OccStatus.OOO, "dec.06.", OccStatus.VACANT, "", true));
		collection.add(new RoomStatusWidget("AB9007", RoomStatus.OOS, "TWINXL", "2-0-2-0", "Partfis Piri", 2, 0,
				OccStatus.OOS, "", OccStatus.CHECKIN, ""));
		collection.add(new RoomStatusWidget("AB9008", RoomStatus.SHOW, "TWINXL", "1-1-0-0", null, 1, 0,
				OccStatus.SHOW, "", OccStatus.VACANT, "", true));
		collection.add(new RoomStatusWidget("AB9009", RoomStatus.DIRTY, "DBLXL", "2-1-1-1", null, 2, 1,
				OccStatus.CHECKOUT, "", OccStatus.OOO, ""));
				*/
	}

	@Override
	public void doFilter(RoomStatusFilterEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(List<RoomStatusDto> data) {
		Boolean odd = false;
		collection.clear();
		for (RoomStatusDto rs: data) {
			RoomStatusWidget rsi = new RoomStatusWidget(rs, odd, i18n);
			rsi.addRoomStatusEditHandler(new RoomStatusEditHandler() {
				@Override
				public void onEdit(RoomStatusEditEvent event) {
					logger.log(Level.INFO, "RoomStatusWidget().onEdit()");
					getUiHandlers().onEdit(event.getRoomStatusDto(), event.getAdmin());
				}
			});
			collection.add(rsi);
			odd = !odd;
		}
	}

}
