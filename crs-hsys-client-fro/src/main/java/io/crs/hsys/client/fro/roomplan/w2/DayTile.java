/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.w2;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
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
	Label unassignedLabel, dateLabel, occupancyLabel;

	/**
	 * 
	 */
	public DayTile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DayTile(Integer unassigned, Date date, Float occupancy) {
		this();
		unassignedLabel.setText(unassigned.toString());
		DateTimeFormat format = DateTimeFormat.getFormat("c d"); // try with "E" pattern also
		String dayText = format.format(date);
		dateLabel.setText(dayText);
		occupancyLabel.setText(occupancy.toString());
	}
}
