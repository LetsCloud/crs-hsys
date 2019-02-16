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
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.shared.dto.hk.MaintenanceSubTotalDto;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

/**
 * @author robi
 *
 */
public class ListMeasureCardGroup2 extends Composite {

	private static ListMeasureCardGroup2UiBinder uiBinder = GWT.create(ListMeasureCardGroup2UiBinder.class);

	interface ListMeasureCardGroup2UiBinder extends UiBinder<Widget, ListMeasureCardGroup2> {
	}

	@UiField
	MaterialColumn basePanel;
	
	@UiField
	Label title;
	
	@UiField
	MaterialButton value, value2;

	@UiField
	Div listPanel;

	/**
	 */
	public ListMeasureCardGroup2(MaintenanceSumDto data, String groupGrid) {
		initWidget(uiBinder.createAndBindUi(this));

		basePanel.setGrid(groupGrid);
		
		setTitle(data.getTitle());
		setValue(value, data.getSubTotals().stream().mapToInt(o -> o.getCol1()).sum());
		setValue(value2, data.getSubTotals().stream().mapToInt(o -> o.getCol2()).sum());

		for (MaintenanceSubTotalDto subTotal : data.getSubTotals()) {
			addItem(subTotal);
		}
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

	public void addItem(MaintenanceSubTotalDto item) {
		listPanel.add(new ListMeasureCardItem2(item));
	}

}
