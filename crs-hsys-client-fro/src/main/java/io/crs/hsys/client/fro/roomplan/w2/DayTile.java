/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.w2;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class DayTile extends Composite {

	private static DayTileUiBinder uiBinder = GWT.create(DayTileUiBinder.class);

	interface DayTileUiBinder extends UiBinder<Widget, DayTile> {
	}

	@UiField
	Label dateLabel, occupancyLabel;

	@UiField
	Label unassignedLabel;

	/**
	 * 
	 */
	public DayTile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DayTile(Integer unassigned, Date date, Float occupancy) {
		this();
		unassignedLabel.setText(unassigned.toString());

		DateTimeFormat dayFormat = DateTimeFormat.getFormat("d"); // try with "E" pattern also
		String dayText = dayFormat.format(date);

		DateTimeFormat weekDayFormat = DateTimeFormat.getFormat("c"); // try with "E" pattern also
		String weekDayText = weekDayFormat.format(date);

		dateLabel.setText(getWeekDayName(weekDayText) + " " + dayText);

		occupancyLabel.setText(NumberFormat.getFormat("##0.00 %").format(occupancy));
	}

	private String getWeekDayName(String weekDayCode) {
		switch (weekDayCode) {
		case "0":
			return "VAS";
		case "1":
			return "HÉT";
		case "2":
			return "KED";
		case "3":
			return "SZE";
		case "4":
			return "CSÜ";
		case "5":
			return "PÉN";
		case "6":
			return "SZO";
		}
		return weekDayCode;
	}
}
