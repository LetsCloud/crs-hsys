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
public class RoomTile extends Composite {

	private static RoomTileUiBinder uiBinder = GWT.create(RoomTileUiBinder.class);

	interface RoomTileUiBinder extends UiBinder<Widget, RoomTile> {
	}

	@UiField
	Label roomLabel;
	
	/**
	 * 
	 */
	public RoomTile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public RoomTile(String text) {
		this();
		roomLabel.setText(text);
	}

}
