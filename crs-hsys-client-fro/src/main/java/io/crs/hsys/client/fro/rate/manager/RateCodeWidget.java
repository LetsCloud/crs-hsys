/**
 * 
 */
package io.crs.hsys.client.fro.rate.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class RateCodeWidget extends Composite {

	private static RateCodeWidgetUiBinder uiBinder = GWT.create(RateCodeWidgetUiBinder.class);

	interface RateCodeWidgetUiBinder extends UiBinder<Widget, RateCodeWidget> {
	}

	@UiField
	MaterialPanel panel;

	@UiField
	Label label;

	/**
	 */
	public RateCodeWidget(String labelText) {
		initWidget(uiBinder.createAndBindUi(this));

		label.setText(labelText);
	}

}
