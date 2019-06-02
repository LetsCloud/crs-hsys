/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.tile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class BookingWidget extends Composite {

	private static BookingWidgetUiBinder uiBinder = GWT.create(BookingWidgetUiBinder.class);

	interface BookingWidgetUiBinder extends UiBinder<Widget, BookingWidget> {
	}

	@UiField
	FocusPanel focusPanel;

	@UiField
	FlowPanel arrowPanel;

	@UiField
	InlineLabel arrowLabel;

	/**
	 * Because this class has a default constructor, it can be used as a binder
	 * template. In other words, it can be used in other *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder" xmlns:g=
	 * "urn:import:**user's package**">
	 * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement
	 * HasHTML instead of HasText.
	 */
	public BookingWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		focusPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
	}

	public void setArrowColor(String style) {
		arrowPanel.addStyleName(style);
	}
	
	public void setTop(double top) {
		focusPanel.getElement().getStyle().setTop(top, Unit.PX);
	}
	
	public void setLeft(double left) {
		focusPanel.getElement().getStyle().setLeft(left, Unit.PX);
	}
}
