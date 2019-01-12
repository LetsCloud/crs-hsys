/**
 * 
 */
package io.crs.hsys.client.fro.dashboard.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.data.SortDir;
import gwt.material.design.client.ui.table.MaterialDataTable;

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
	MaterialDataTable<ReservationSearchDto> table;

	/**
	 */
	public ReservationGrid() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	private void initTable() {
		table.setTitle("Foglalások");

		// Code Column
		table.addColumn(new DataColumn<ReservationSearchDto>((object) -> object.getId().toString(),
				(o1, o2) -> o1.getData().getId().compareTo(o2.getData().getId())), "R-No");

		// Arrival Column
		table.addColumn(new DataColumn<ReservationSearchDto>((object) -> object.getArrival().toString()), "Érkezés");

		// Departure Column
		table.addColumn(new DataColumn<ReservationSearchDto>((object) -> object.getDeparture().toString()), "Távozás");

		// Name Column
		table.addColumn(new DataColumn<ReservationSearchDto>((object) -> object.getName(),
				(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())), "Név");

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
