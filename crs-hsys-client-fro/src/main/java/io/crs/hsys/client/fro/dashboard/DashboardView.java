/**
 * 
 */
package io.crs.hsys.client.fro.dashboard;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import gwt.material.design.incubator.client.toggle.GroupToggleButton;

import io.crs.hsys.client.fro.dashboard.widget.MeasureCard;
import io.crs.hsys.client.fro.dashboard.widget.ReservationGrid;
import io.crs.hsys.shared.dto.reservation.ReservationSearchDto;

/**
 * @author CR
 *
 */
public class DashboardView extends ViewWithUiHandlers<DashboardUiHandlers> implements DashboardPresenter.MyView {
	private static Logger logger = Logger.getLogger(DashboardView.class.getName());

	interface Binder extends UiBinder<Widget, DashboardView> {
	}

	private ReservationGrid reservationGrid;

	@UiField
	MaterialRow measuresPanel;

	@UiField
	SimplePanel reservationSearchPanel;

	@UiField
	GroupToggleButton<Integer> periodToggle, detailToggle;

	@UiField
	MeasureCard arrivals, departures, occupancy;

	@Inject
	DashboardView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		logger.log(Level.INFO, "DashboardView");
		periodToggle.addItem("Ma", 1);
		periodToggle.addItem("Holnap", 2);
		periodToggle.addItem("A héten", 3);
		periodToggle.setActive(1);

		detailToggle.addItem("Foglalás", 1);
		detailToggle.addItem("Szoba", 2);
		detailToggle.addItem("Vendég", 3);
		detailToggle.setActive(1);

		reservationGrid = new ReservationGrid();
		reservationSearchPanel.add(reservationGrid);
	}

	@Override
	public void showCards() {
		new MaterialAnimation().transition(Transition.SHOW_GRID).animate(measuresPanel);
	}

	@Override
	public void setReservationSearchData(List<ReservationSearchDto> data) {
		reservationGrid.setData(data);
	}
}
