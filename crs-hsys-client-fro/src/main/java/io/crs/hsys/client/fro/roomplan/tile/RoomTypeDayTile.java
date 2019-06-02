/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.tile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class RoomTypeDayTile extends Composite {

	private static RoomTypeDayTileUiBinder uiBinder = GWT.create(RoomTypeDayTileUiBinder.class);

	interface RoomTypeDayTileUiBinder extends UiBinder<Widget, RoomTypeDayTile> {
	}

	@UiField
	Label availableLabel, priceLabel;

	/**
	 * 
	 */
	public RoomTypeDayTile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public RoomTypeDayTile(Integer available, Double occupancy) {
		this();
		availableLabel.setText(available.toString());
		priceLabel.setText(occupancy.toString());
	}
}
