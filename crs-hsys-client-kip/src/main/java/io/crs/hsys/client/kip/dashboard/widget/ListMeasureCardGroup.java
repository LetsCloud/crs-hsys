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

import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardGroup extends Composite {

	private static ListMeasureCardGroupUiBinder uiBinder = GWT.create(ListMeasureCardGroupUiBinder.class);

	interface ListMeasureCardGroupUiBinder extends UiBinder<Widget, ListMeasureCardGroup> {
	}

	@UiField
	Label title, col1, col2, col3;

	@UiField
	Div listPanel;

	/**
	 */
	public ListMeasureCardGroup(MaintenanceSumDto data) {
		initWidget(uiBinder.createAndBindUi(this));

		setTitle(data.getTitle());
		setValue(col1, data.getSubTotals().stream().mapToInt(o -> o.getCol1()).sum());
		setValue(col2, data.getSubTotals().stream().mapToInt(o -> o.getCol2()).sum());
		setValue(col3, data.getSubTotals().stream().mapToInt(o -> o.getCol3()).sum());

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
		listPanel.add(new ListMeasureCardItem(item));
	}

}
