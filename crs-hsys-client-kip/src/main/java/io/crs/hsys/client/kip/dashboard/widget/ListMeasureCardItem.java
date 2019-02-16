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

import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardItem extends Composite {

	private static ListMeasureCardItemUiBinder uiBinder = GWT.create(ListMeasureCardItemUiBinder.class);

	interface ListMeasureCardItemUiBinder extends UiBinder<Widget, ListMeasureCardItem> {
	}

	@UiField
	Label title, col1, col2, col3;

	/**
	 */
	public ListMeasureCardItem(MaintenanceSubTotalDto data) {
		initWidget(uiBinder.createAndBindUi(this));

		setTitle(data.getTitle());
		setValue(col1, data.getCol1());
		setValue(col2, data.getCol2());
		setValue(col3, data.getCol3());
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
