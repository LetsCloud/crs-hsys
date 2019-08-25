/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.shared.dto.reservation.ReservationDto;
import io.crs.hsys.shared.dto.reservation.RoomStayDto;

/**
 * @author robi
 *
 */
public class RoomResView extends ViewWithUiHandlers<RoomResUiHandlers> implements RoomStayPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomResView.class.getName());

	interface Binder extends UiBinder<Widget, RoomResView> {
	}
	
	@UiField
	FlowPanel roomStaysPanel;
	
	/**
	*/
	@Inject
	RoomResView(Binder binder) {
		logger.log(Level.INFO, "RoomResView()");
		initWidget(binder.createAndBindUi(this));
	}
	
	@UiHandler("checkin")
	public void onCheckIn(ClickEvent event) {
	}

	@Override
	public void setData(ReservationDto reservation) {
		roomStaysPanel.clear();
		for(RoomStayDto rsd: reservation.getRoomStays()) {
			roomStaysPanel.add(new RoomDisplay(rsd));  
		}
		
		// TODO Auto-generated method stub
		
	}
}
