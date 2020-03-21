/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author CR
 *
 */
public class MultiMeasurePanel extends Composite {

	interface Binder extends UiBinder<Widget, MultiMeasurePanel> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	@UiField
	MaterialPanel panel;

	/**
	 */
	public MultiMeasurePanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addMeasure(Widget measure) {
		panel.add(measure);
	}

	public void clear() {
		panel.clear();
	}
}
