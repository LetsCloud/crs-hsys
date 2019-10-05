/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class DayWidget extends Composite {

	private static DayWidgetUiBinder uiBinder = GWT.create(DayWidgetUiBinder.class);

	interface DayWidgetUiBinder extends UiBinder<Widget, DayWidget> {
	}

	@UiField
	MaterialPanel contentPanel;

	@UiField
	MaterialLabel label;

	/**
	 * 
	 */
	public DayWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	public void setFloat(com.google.gwt.dom.client.Style.Float floatAlign) {
		contentPanel.setFloat(floatAlign);
	}

	public void setWidth(String width) {
		contentPanel.setWidth(width);
	}
}
