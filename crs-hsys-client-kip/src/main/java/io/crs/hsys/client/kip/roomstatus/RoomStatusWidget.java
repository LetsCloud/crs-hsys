/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addext.client.ui.MaterialDesignIcon;
import gwt.material.design.addext.client.ui.constants.MdiType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;

/**
 * @author robi
 *
 */
public class RoomStatusWidget extends Composite {

	private static RoomStatusWidgetUiBinder uiBinder = GWT.create(RoomStatusWidgetUiBinder.class);

	interface RoomStatusWidgetUiBinder extends UiBinder<Widget, RoomStatusWidget> {
	}

	@UiField
	MaterialRow roomStatusPanel;

	@UiField
	MaterialColumn currOccStatusPanel, nextOccStatusPanel;

	@UiField
	MaterialDesignIcon statusIcon, currOccStatus, nextOccStatus;

	@UiField
	Label roomNo, roomType, atendant;

	@UiField
	InlineLabel guestNumber, cleaning, maintenance, currOccLabel, nextOccLabel;

	/**
	 * 
	 */
	public RoomStatusWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public RoomStatusWidget(String roomNo, RoomStatus roomStatus, String roomType, String guestNumber, String atendant,
			Integer cleaningTasks, Integer maintTasks, OccStatus currOccStatus, String currOccLabel,
			OccStatus nextOccStatus, String nextOccLabel) {
		this();
		roomStatusPanel.setBackgroundColor(getStatusBgColor(roomStatus));
		statusIcon.setTextColor(getStatusIconColor(roomStatus));
		statusIcon.setIconType(getStatusIcon(roomStatus));
		this.roomNo.setText(roomNo);
		this.roomType.setText(roomType);
		this.guestNumber.setText(guestNumber);
		if (atendant == null)
			this.atendant.setText("BEOSZTATLAN");
		else
			this.atendant.setText(atendant);
		cleaning.setText(cleaningTasks.toString());
		maintenance.setText(maintTasks.toString());
		currOccStatusPanel.setTextColor(getOccStatusColor(currOccStatus));
		this.currOccStatus.setIconType(getOccStatusIcon(currOccStatus));
		this.currOccLabel.setText(currOccLabel);
		nextOccStatusPanel.setTextColor(getOccStatusColor(nextOccStatus));
		this.nextOccStatus.setIconType(getOccStatusIcon(nextOccStatus));
		this.nextOccLabel.setText(nextOccLabel);
	}

	private Color getStatusBgColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED;
		case CLEAN:
			return Color.BLUE;
		case INSPECTED:
			return Color.GREEN;
		case OOO:
			return Color.BLACK;
		case OOS:
			return Color.PURPLE;
		case SHOW:
			return Color.AMBER;
		default:
			break;
		}
		return null;
	}

	private Color getStatusIconColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED_LIGHTEN_4;
		case CLEAN:
			return Color.BLUE_LIGHTEN_4;
		case INSPECTED:
			return Color.GREEN_LIGHTEN_4;
		case OOO:
			return Color.GREY_LIGHTEN_4;
		case OOS:
			return Color.PURPLE_LIGHTEN_4;
		case SHOW:
			return Color.AMBER_LIGHTEN_4;
		default:
			break;
		}
		return null;
	}

	private MdiType getStatusIcon(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return MdiType.DELETE_CIRCLE_OUTLINE;
		case CLEAN:
			return MdiType.STAR_CIRCLE_OUTLINE;
		case INSPECTED:
			return MdiType.CHECK_CIRCLE_OUTLINE;
		case OOO:
			return MdiType.SETTINGS_OUTLINE;
		case OOS:
			return MdiType.CLOSE_CIRCLE_OUTLINE;
		case SHOW:
			return MdiType.EYE_OUTLINE;
		default:
			break;
		}
		return null;
	}

	private Color getOccStatusColor(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return Color.GREY;
		case EARLYCI:
			return Color.GREEN;
		case CHECKIN:
			return Color.GREEN;
		case INHOUSE:
			return Color.BLUE;
		case CHECKOUT:
			return Color.RED;
		case LATECO:
			return Color.RED;
		case OOO:
			return Color.BLACK;
		case OOS:
			return Color.PURPLE;
		case SHOW:
			return Color.AMBER;
		default:
			break;
		}
		return null;
	}

	private MdiType getOccStatusIcon(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return MdiType.CHECKBOX_BLANK;
		case EARLYCI:
			return MdiType.ALPHA_E_BOX;
		case CHECKIN:
			return MdiType.ARROW_DOWN_BOX;
		case INHOUSE:
			return MdiType.ACCOUNT_BOX;
		case CHECKOUT:
			return MdiType.ARROW_UP_BOX;
		case LATECO:
			return MdiType.ALPHA_L_BOX;
		case OOO:
			return MdiType.SETTING_BOX;
		case OOS:
			return MdiType.CLOSE_BOX;
		case SHOW:
			return MdiType.EYE;
		default:
			break;
		}
		return null;
	}
}
