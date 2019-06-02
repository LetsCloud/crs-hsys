/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.tile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;

/**
 * @author robi
 *
 */
public class RoomTypeTile extends Composite {

	private static RoomTypeTileUiBinder uiBinder = GWT.create(RoomTypeTileUiBinder.class);

	interface RoomTypeTileUiBinder extends UiBinder<Widget, RoomTypeTile> {
	}

	@UiField
	MaterialLink roomTypeLink;

	/**
	 * 
	 */
	public RoomTypeTile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public RoomTypeTile(String text) {
		this();
		roomTypeLink.setText(text);
	}

}
