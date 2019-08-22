/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
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
	ValueDisplay arrivalDisplay;
	
	/**
	*/
	@Inject
	RoomResView(Binder binder) {
		logger.log(Level.INFO, "RoomResView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void open() {
		Integer nightsValue = 2;
		Date depDate = new Date();
		CalendarUtil.addDaysToDate(depDate, nightsValue);
	}
	
	@UiHandler("checkin")
	public void onCheckIn(ClickEvent event) {
	}
}
