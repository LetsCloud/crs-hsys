/**
 * 
 */
package io.crs.hsys.client.fro.dashboard.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.data.SortDir;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.shared.dto.reservation.ReservationSearchDto;

/**
 * @author robi
 *
 */
public class ReservationGrid extends Composite {

	private static ReservationGridUiBinder uiBinder = GWT.create(ReservationGridUiBinder.class);

	interface ReservationGridUiBinder extends UiBinder<Widget, ReservationGrid> {
	}

	@UiField
	ReservationDataTable<ReservationSearchDto> table;

	/**
	 */
	public ReservationGrid() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	private void initTable() {
		table.setTitle("Foglalások");

		// Code Column
		table.addColumn("R-No", new DataColumn<ReservationSearchDto>((object) -> object.getId().toString(),
				(o1, o2) -> o1.getData().getId().compareTo(o2.getData().getId())));

		// Arrival Column
		table.addColumn("Érkezés", new DataColumn<ReservationSearchDto>(
				(object) -> DateTimeFormat.getShortDateFormat().format(object.getArrival())));

		// Departure Column
		table.addColumn("Távozás", new DataColumn<ReservationSearchDto>(
				(object) -> DateTimeFormat.getShortDateFormat().format(object.getDeparture())));

		// Name Column
		table.addColumn("Név", new DataColumn<ReservationSearchDto>((object) -> object.getName(),
				(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())));

		// Edit Column
		table.addColumn(new ActionColumn<ReservationSearchDto>((object) -> edit(object)));
	}

	public void edit(ReservationSearchDto o) {
	};

	public void setData(List<ReservationSearchDto> data) {
		table.setVisibleRange(0, data.size());
		table.setRowData(0, data);
		table.sort(0, SortDir.ASC);
	}
}
