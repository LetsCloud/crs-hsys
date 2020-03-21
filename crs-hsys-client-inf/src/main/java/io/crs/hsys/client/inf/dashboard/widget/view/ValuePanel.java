/**
 * 
 */
package io.crs.hsys.client.inf.dashboard.widget.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author CR
 *
 */
public class ValuePanel extends Composite {
	private static Logger logger = Logger.getLogger(ValuePanel.class.getName());

	interface Binder extends UiBinder<Widget, ValuePanel> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	private String format = "#,##0";

	private String prefix;

	private String sufix;

	private Float multiplier;

	@UiField
	MaterialPanel panel;

	@UiField
	Label valueField;

	@UiField
	Label remarkLabel;

	/**
	 */
	public ValuePanel() {
		logger.log(Level.INFO, "ValuePanel()");
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

	public void addWidget(Widget widget) {
		panel.add(widget);
	}

	public void setRemark(String remark) {
		remarkLabel.setText(remark);
	}
}
