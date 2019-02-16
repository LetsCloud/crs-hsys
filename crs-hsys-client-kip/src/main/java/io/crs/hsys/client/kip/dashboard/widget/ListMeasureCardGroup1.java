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

import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardGroup1 extends Composite {

	private static ListMeasureCardGroup1UiBinder uiBinder = GWT.create(ListMeasureCardGroup1UiBinder.class);

	interface ListMeasureCardGroup1UiBinder extends UiBinder<Widget, ListMeasureCardGroup1> {
	}

	@UiField
	Label title, value;

	@UiField
	Div listPanel;

	/**
	 */
	public ListMeasureCardGroup1(MaintenanceSumDto data) {
		initWidget(uiBinder.createAndBindUi(this));

		setTitle(data.getTitle());
		setValue(value, data.getSubTotals().stream().mapToInt(o -> o.getCol1()).sum());

		for (MaintenanceSubTotalDto subTotal : data.getSubTotals()) {
			addItem(subTotal);
		}
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

	public void addItem(MaintenanceSubTotalDto item) {
		listPanel.add(new ListMeasureCardItem1(item));
	}

}
