/**
 * 
 */
package io.crs.hsys.client.kip.browser.guestroom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author robi
 *
 */
public class GuestRoomWidget extends Composite {

	private static GuestRoomWidgetUiBinder uiBinder = GWT.create(GuestRoomWidgetUiBinder.class);

	interface GuestRoomWidgetUiBinder extends UiBinder<Widget, GuestRoomWidget> {
	}

	@UiField
	MaterialChip roomLabel;

	@UiField
	MaterialLabel description, roomType;

	@UiField
	MaterialLabel inspectorLabel;

	@UiField
	MaterialLabel cleanTypeLabel;

	@UiField
	MaterialLabel cleanTimeLabel;

	@UiField
	MaterialLabel reservationNoLabel;

	@UiField
	MaterialLabel nextArrivalLabel;

	/**
	 * 
	 */
	public GuestRoomWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public GuestRoomWidget(RoomDto data) {
		this();
		
		roomLabel.setText(data.getCode());
		roomLabel.setTextColor(Color.BLACK);
		roomLabel.setBackgroundColor(Color.GREY_LIGHTEN_3);
		roomLabel.setLetter("D");
		roomLabel.setLetterBackgroundColor(Color.RED);
		roomLabel.setLetterColor(Color.WHITE);

		description.setText(data.getDescription());
		
		roomType.setText(data.getCode());
	}
}
