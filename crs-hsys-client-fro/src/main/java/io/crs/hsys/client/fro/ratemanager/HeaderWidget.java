/**
 * 
 */
package io.crs.hsys.client.fro.ratemanager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class HeaderWidget extends Composite {

	private static HeaderWidgetUiBinder uiBinder = GWT.create(HeaderWidgetUiBinder.class);

	interface HeaderWidgetUiBinder extends UiBinder<Widget, HeaderWidget> {
	}

	@UiField
	MaterialPanel wrapPanel;

	@UiField
	Label weekDayLabel, monthDayLabel;

	/**
	 * Because this class has a default constructor, it can be used as a binder
	 * template. In other words, it can be used in other *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder" xmlns:g=
	 * "urn:import:**user's package**">
	 * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement
	 * HasHTML instead of HasText.
	 */
	public HeaderWidget(String weekDay, String monthDay) {
		initWidget(uiBinder.createAndBindUi(this));
		weekDayLabel.setText(weekDay);
		monthDayLabel.setText(monthDay);
	}

}
