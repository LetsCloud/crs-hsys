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
public class MultiMeasureField extends Composite {

	interface Binder extends UiBinder<Widget, MultiMeasureField> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	private String format = "#,##0";

	private String prefix;

	private String sufix;

	private Float multiplier;

	@UiField
	Label valueLabel;

	@UiField
	Label valueField;

	/**
	 */
	public MultiMeasureField() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setFormat(String format) {
		if ((format != null) && (!format.isEmpty()))
			this.format = format;
	}

	public void setPrefix(String prefix) {
		if ((prefix != null) && (!prefix.isEmpty()))
			this.prefix = prefix;
	}

	public void setSufix(String sufix) {
		if ((sufix != null) && (!sufix.isEmpty()))
			this.sufix = sufix;
	}

	public void setMultiplier(Float multiplier) {
		if ((multiplier != null) && (multiplier != 0))
			this.multiplier = multiplier;
	}

	public void setLabelText(String text) {
		valueLabel.setText(text);
	}

	public void setValue(Double value) {
		if (multiplier != null)
			value = value * multiplier;
		String text = NumberFormat.getFormat(format).format(value);
		if (prefix != null)
			text = prefix + text;
		if (sufix != null)
			text = text + sufix;
		// LOGGER.log(Level.INFO, "setValue()->text=" + text + ",sufix=" +
		// sufix);
		valueField.setText(text);
	}
}
