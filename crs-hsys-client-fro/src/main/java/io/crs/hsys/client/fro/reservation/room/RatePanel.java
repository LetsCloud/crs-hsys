/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class RatePanel extends Composite {

	private static RatePanelUiBinder uiBinder = GWT.create(RatePanelUiBinder.class);

	interface RatePanelUiBinder extends UiBinder<Widget, RatePanel> {
	}

	MaterialLabel weekDayLabel;
	MaterialPanel panel;
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
	public RatePanel() {
		initWidget(uiBinder.createAndBindUi(this));
	
	}

}
