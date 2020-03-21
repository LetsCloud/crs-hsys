/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author CR
 *
 */
public class DeltaField extends Composite {

	interface Binder extends UiBinder<Widget, DeltaField> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	@UiField
	Label deltaLabel;

	/**
	 */
	public DeltaField() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setDelta(Double change) {
		String text = NumberFormat.getFormat("##0.00").format(change) + " %";
		if (change > 0) {
			deltaLabel.getElement().getStyle().setColor("green");
			text = "+ " + text;
		}
		if (change < 0)
			deltaLabel.getElement().getStyle().setColor("red");

		deltaLabel.setText(text);
	}
}
