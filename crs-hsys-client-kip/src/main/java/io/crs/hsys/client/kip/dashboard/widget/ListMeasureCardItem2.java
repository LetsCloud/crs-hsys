/**
 * 
 */
package io.crs.hsys.client.kip.dashboard.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardItem2 extends Composite {

	private static ListMeasureCardItem2UiBinder uiBinder = GWT.create(ListMeasureCardItem2UiBinder.class);

	interface ListMeasureCardItem2UiBinder extends UiBinder<Widget, ListMeasureCardItem2> {
	}

	@UiField
	Label title;
	
	@UiField
	MaterialButton value, value2;

	/**
	 */
	public ListMeasureCardItem2(MaintenanceSubTotalDto data) {
		initWidget(uiBinder.createAndBindUi(this));

		setTitle(data.getTitle());
		setValue(value, data.getCol1());
		setValue(value2, data.getCol2());
	}

	public void setTitle(String text) {
		title.setText(text);
	}

	public void setValue(MaterialButton label, Integer value) {
		if (value == 0)
			label.setText("-");
		else
			label.setText(value.toString());
	}
}
