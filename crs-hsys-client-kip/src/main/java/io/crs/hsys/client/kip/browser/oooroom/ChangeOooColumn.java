/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.Date;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;

import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
public class ChangeOooColumn extends ActionColumn<OooRoomDto> {

	private Date today;

	public ChangeOooColumn(ActionRow<OooRoomDto> actionRow, Date today) {
		super(actionRow);
		this.today = today;
	}

	@Override
	protected MaterialIcon getIcon(OooRoomDto object) {
		MaterialIcon icon = super.getIcon(object);
		if (!isVisible(object))
			return icon;

		if (object.getFromDate().equals(today)) {
			icon.setIconType(IconType.LOCK_OUTLINE);
			icon.setBackgroundColor(Color.RED);
			return icon;
		}

		if (object.getToDate().equals(today)) {
			icon.setIconType(IconType.LOCK_OPEN);
			icon.setBackgroundColor(Color.GREEN);
			return icon;
		}

		return icon;
	}

}
