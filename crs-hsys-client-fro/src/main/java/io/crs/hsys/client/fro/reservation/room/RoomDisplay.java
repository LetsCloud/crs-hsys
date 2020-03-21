/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLabel;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.shared.dto.reservation.RoomStayDto;

/**
 * @author robi
 *
 */
public class RoomDisplay extends Composite {

	private static RoomDisplayUiBinder uiBinder = GWT.create(RoomDisplayUiBinder.class);

	interface RoomDisplayUiBinder extends UiBinder<Widget, RoomDisplay> {
	}
	
	@UiField
	ValueDisplay arrivalDisplay, departurelDisplay, roomDisplay, guestDisplay;

	/**
	 * 
	 */
	public RoomDisplay(RoomStayDto roomStayDto) {
		initWidget(uiBinder.createAndBindUi(this));
		
		if (roomStayDto.getMovedInto()) {
			arrivalDisplay.setIconType(IconType.ARROW_DOWNWARD);
			arrivalDisplay.setIconColor(Color.GREY);
			arrivalDisplay.setLabel("Beköltözés");
		}
		
		if (roomStayDto.getEci() ) {
			arrivalDisplay.addBadge(createBadge("KORAI", Color.GREEN, Color.WHITE));
		}

		arrivalDisplay.setText(DateUtils.formatDate(roomStayDto.getArrival(), "hu"));
		arrivalDisplay.setSubText(DateUtils.getDayOfWeek3(roomStayDto.getArrival(), "hu"));
		
		if (roomStayDto.getMovedOut()) {
			departurelDisplay.setIconType(IconType.ARROW_UPWARD);
			departurelDisplay.setIconColor(Color.GREY);
			departurelDisplay.setLabel("Kiköltözés");
		}
		
		if (roomStayDto.getLco() ) {
			departurelDisplay.addBadge(createBadge("KÉSŐI", Color.RED, Color.WHITE));
		}
		
		departurelDisplay.setText(DateUtils.formatDate(roomStayDto.getDeparture(), "hu"));
		departurelDisplay.setSubText(DateUtils.getDayOfWeek3(roomStayDto.getDeparture(), "hu"));
		
		
		if (roomStayDto.getFixed() ) {
			departurelDisplay.addBadge(createBadge("FIX", Color.BLUE, Color.WHITE));
		}
		
		if (roomStayDto.getNoPost() ) {
			departurelDisplay.addBadge(createBadge("NO POST", Color.BLACK, Color.WHITE));
		}

		roomDisplay.setText(roomStayDto.getRoom().getCode());
		roomDisplay.setSubText(roomStayDto.getRoomType().getCode());
	}

	private MaterialLabel createBadge(String text, Color backgroundColor, Color textColor) {
		MaterialLabel badge = new MaterialLabel(text, textColor);
		badge.setBackgroundColor(backgroundColor);
		badge.setDisplay(Display.INLINE);
		badge.setPaddingLeft(2);
		badge.setPaddingRight(2);
		badge.setMarginLeft(10);
		badge.setBorderRadius("3px");
		badge.setFontSize(12, Unit.PX);
		badge.setFontWeight(FontWeight.BOLD);
		return badge;
	}
	public void setData() {
		
	}
}
