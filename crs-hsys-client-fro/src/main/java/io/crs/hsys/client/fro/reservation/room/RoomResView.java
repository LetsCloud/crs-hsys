/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialFloatBox;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author robi
 *
 */
public class RoomResView extends ViewWithUiHandlers<RoomResUiHandlers> implements RoomResPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomResView.class.getName());

	interface Binder extends UiBinder<Widget, RoomResView> {
	}
	
	@UiField
	MaterialTextBox guetsName;
	
	@UiField
	MaterialDatePicker arrival, departure;

	@UiField
	MaterialIntegerBox nights, adults, tinies, children, infants;
	
	@UiField
	MaterialFloatBox rate;
	
	/**
	*/
	@Inject
	RoomResView(Binder binder) {
		logger.log(Level.INFO, "RoomResView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void open() {
		arrival.setValue(new Date());
		Integer nightsValue = 2;
		nights.setValue(nightsValue);
		Date depDate = new Date();
		CalendarUtil.addDaysToDate(depDate, nightsValue);
		departure.setValue(depDate);
		rate.setValue(999999f);
	}
	
	@UiHandler("checkin")
	public void onCheckIn(ClickEvent event) {
		guetsName.setReadOnly(true);
		adults.setReadOnly(true); 
		tinies.setReadOnly(true); 
		children.setReadOnly(true); 
		infants.setReadOnly(true);
	}
}
