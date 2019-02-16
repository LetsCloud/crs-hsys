/**
 * 
 */
package io.crs.hsys.client.kip.dashboard.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.masonry.MaterialMasonry;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.shared.dto.hk.MaintenanceSumDto;

/**
 * @author robi
 *
 */
public class ListMeasureCard extends Composite {

	private static ListMeasureCardUiBinder uiBinder = GWT.create(ListMeasureCardUiBinder.class);
	
	interface ListMeasureCardUiBinder extends UiBinder<Widget, ListMeasureCard> {
	}

	@UiField
	MaterialLink titleLink;

	@UiField
	Div frame;
	
	@UiField
	MaterialMasonry content;
	
	/**
	 */
	public ListMeasureCard(List<MaintenanceSumDto> data, String titleText, IconType titleIcon, String groupGrid) {
		initWidget(uiBinder.createAndBindUi(this));

		titleLink.setText(titleText);
		titleLink.setIconType(titleIcon);

		content.clear();
		for (MaintenanceSumDto dataItem : data) {
			content.add(new ListMeasureCardGroup2(dataItem, groupGrid));
		}
	}
}
