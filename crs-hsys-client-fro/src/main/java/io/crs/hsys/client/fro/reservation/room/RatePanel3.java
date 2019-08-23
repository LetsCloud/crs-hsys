/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;

/**
 * @author CR
 *
 */
public class RatePanel3 extends Composite {

	private static RatePanel3UiBinder uiBinder = GWT.create(RatePanel3UiBinder.class);

	interface RatePanel3UiBinder extends UiBinder<Widget, RatePanel3> {
	}

	@UiField
	MaterialIcon arrivalIcon, departureIcon ;
	
	@UiField
	MaterialLabel rateCodeLabel, rateLabel;

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	  *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public RatePanel3() {
		initWidget(uiBinder.createAndBindUi(this));
		arrivalIcon.setVisible(false);
		departureIcon.setVisible(false);
		rateCodeLabel.setFontSize(20, Unit.PX);
		rateLabel.setFontSize(20, Unit.PX);
		rateLabel.setMarginRight(5);
	}

	public void setEnableArrival(Boolean arrival) {
		arrivalIcon.setVisible(arrival);

	}

	public void setEnableDeparture(Boolean arrival) {
		departureIcon.setVisible(arrival);

	}

}
