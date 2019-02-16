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

import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialButton;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardItem1 extends Composite {

	private static ListMeasureCardItem1UiBinder uiBinder = GWT.create(ListMeasureCardItem1UiBinder.class);

	interface ListMeasureCardItem1UiBinder extends UiBinder<Widget, ListMeasureCardItem1> {
	}

	@UiField
	Label value;
	
	@UiField
	MaterialButton title;

	/**
	 */
	public ListMeasureCardItem1(MaintenanceSubTotalDto data) {
		initWidget(uiBinder.createAndBindUi(this));

		title.setTextAlign(TextAlign.LEFT);
		setTitle(data.getTitle());
		setValue(value, data.getCol1());
	}

	public void setTitle(String text) {
		title.setText(text);
	}

	public void setValue(Label label, Integer value) {
		if (value == 0)
			label.setText("-");
		else
			label.setText(value.toString());
	}
}
