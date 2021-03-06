/**
 * 
 */
package io.crs.hsys.client.fro.roomplan;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.fro.resources.FroGssResources;
import io.crs.hsys.client.fro.roomplan.model.HeaderData;
import io.crs.hsys.client.fro.roomplan.model.RoomCellData;
import io.crs.hsys.client.fro.roomplan.model.RoomTypeRowData;
import io.crs.hsys.client.fro.roomplan.tile.BookingWidget;
import io.crs.hsys.client.fro.roomplan.tile.DayTile;
import io.crs.hsys.client.fro.roomplan.tile.RoomTile;
import io.crs.hsys.client.fro.roomplan.tile.RoomTypeDayTile;
import io.crs.hsys.client.fro.roomplan.tile.RoomTypeTile;
import io.crs.hsys.client.fro.roomplan.widget.BookingStyle;
import io.crs.hsys.client.fro.roomplan.widget.DateUtils;
import io.crs.hsys.client.fro.roomplan.widget.RoomPlanSettings;

/**
 * @author CR
 *
 */
public class RoomPlanView extends ViewWithUiHandlers<RoomPlanUiHandlers> implements RoomPlanPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomPlanView.class.getName());

	interface Binder extends UiBinder<Widget, RoomPlanView> {
	}

	private final static String CELL_STYLE = "dayCell";
	private final static String CELL_HEADER_STYLE = "dayCellLabel";
	private final static String WEEKDAY_LABEL_STYLE = "weekDayLabel";

	/**
	 * The first date displayed on the MonthView (1st cell.) This date is not
	 * necessarily the first date of the month as the month view will sometimes
	 * display days from the adjacent months because of the number of days fitting
	 * in the visible grid.
	 */
	private Date firstDateDisplayed;

	/**
	 * The number of rows required to display the entire month in grid format.
	 * Although most months span a total of five weeks, there are some months that
	 * span six weeks.
	 */
	private int numberOfRooms = 5;

	private RoomPlanSettings settings = RoomPlanSettings.DEFAULT_SETTINGS;

	@UiField
	FlexTable roomPlanGrid, bookingGrid;

	@UiField
	AbsolutePanel bookingCanvas;

	private final io.crs.hsys.client.fro.resources.FroGssResources.BookingStyle bookingStyle;
	/*
	 * @UiField AbsolutePanel bookingCanvas;
	 */
	@Inject
	RoomPlanView(Binder uiBinder, FroGssResources froGssRes) {
		initWidget(uiBinder.createAndBindUi(this));
		logger.log(Level.INFO, "RoomPlanView");
		this.bookingStyle = froGssRes.bookingStyle();
		bookingCanvas.getElement().getStyle().setPosition(Position.ABSOLUTE);
	}

	/**
	 * This method is called when the MonthView is attached to the Calendar and
	 * displayed. This is where all components are configured and added to the
	 * RootPanel.
	 */
	@Override
	public void onAttach() {
		super.onAttach();
		roomPlanGrid.setCellPadding(0);
		roomPlanGrid.setBorderWidth(1);
		roomPlanGrid.setCellSpacing(0);

	}

	public void doLayout() {
		// Clear all existing bookings

//		bookingCanvas.clear();
		roomPlanGrid.clear();
		while (roomPlanGrid.getRowCount() > 0) {
			roomPlanGrid.removeRow(0);
		}

		// Rebuild the month grid
//		buildRoomPlanGrid();

	}

	/**
	 * Builds and formats the Calendar Grid. No appointments are included when
	 * building the grid.
	 */
	@SuppressWarnings("deprecation")
	private void buildRoomPlanGrid() {
		int daysInPeriod = 10;
		firstDateDisplayed = new Date();

		Date today = new Date();
		DateUtils.resetTime(today);

		/* Add the roomplan days heading */
		for (int i = 0; i < daysInPeriod; i++) {
//			roomPlanGrid.setText(0, i,
//					CalendarFormat.INSTANCE.getDayOfWeekAbbreviatedNames()[(i + firstDayOfWeek) % 7]);
			roomPlanGrid.setText(0, i, "A hét napja");
			roomPlanGrid.getCellFormatter().setVerticalAlignment(0, i, HasVerticalAlignment.ALIGN_TOP);
			roomPlanGrid.getCellFormatter().setStyleName(0, i, WEEKDAY_LABEL_STYLE);
		}

		Date date = (Date) firstDateDisplayed.clone();
		for (int roomPlanGridRowIndex = 1; roomPlanGridRowIndex <= numberOfRooms; roomPlanGridRowIndex++) {
			for (int dayCellIndex = 0; dayCellIndex < daysInPeriod; dayCellIndex++) {

				configureDayInGrid(roomPlanGridRowIndex, dayCellIndex, date, date.equals(today), 99);
			}
		}
	}

	/**
	 * Configures a single day in the month grid of this <code>MonthView</code>.
	 *
	 * @param row               The row in the grid on which the day will be set
	 * @param col               The col in the grid on which the day will be set
	 * @param date              The Date in the grid
	 * @param isToday           Indicates whether the day corresponds to today in
	 *                          the month view
	 * @param notInCurrentMonth Indicates whether the day is in the current
	 *                          visualized month or belongs to any of the two
	 *                          adjacent months of the current month
	 * @param weekNumber        The weekNumber to show in the cell, only appears in
	 *                          the first col.
	 */
	private void configureDayInGrid(int row, int col, Date date, boolean isToday, int weekNumber) {
		HorizontalPanel panel = new HorizontalPanel();
		String text = String.valueOf(date.getDate());
		Label label = new Label(text);

		StringBuilder headerStyle = new StringBuilder(CELL_HEADER_STYLE);
		StringBuilder cellStyle = new StringBuilder(CELL_STYLE);
		boolean found = false;

		for (Date day : getSettings().getHolidays()) {
			if (DateUtils.areOnTheSameDay(day, date)) {
				headerStyle.append("-holiday");
				cellStyle.append("-holiday");
				found = true;
				break;
			}
		}

		if (isToday) {
			headerStyle.append("-today");
			cellStyle.append("-today");
		} else if (!found && DateUtils.isWeekend(date)) {
			headerStyle.append("-weekend");
			cellStyle.append("-weekend");
		}

		label.setStyleName(headerStyle.toString());
//		addDayClickHandler(label, (Date) date.clone());

		if (col == 0 && getSettings().isShowingWeekNumbers()) {
			Label weekLabel = new Label(String.valueOf(weekNumber));
//			weekLabel.setStyleName(WEEKNUMBER_LABEL_STYLE);

			panel.add(weekLabel);
			panel.setCellWidth(weekLabel, "25px");
			label.getElement().getStyle().setPaddingLeft(5, Unit.PX);
//			addWeekClickHandler(weekLabel, (Date) date.clone());
		}
		panel.add(label);

//		bookingCanvas.add(panel);
//		dayLabels.add(label);
//		dayPanels.add(panel);

		// monthCalendarGrid.setWidget(row, col, panel);
		roomPlanGrid.getCellFormatter().setVerticalAlignment(row, col, HasVerticalAlignment.ALIGN_TOP);
		roomPlanGrid.getCellFormatter().setStyleName(row, col, cellStyle.toString());
	}

	public RoomPlanSettings getSettings() {
		return settings;
	}

	@Override
	public void showPage() {
		doLayout();
	}

	@Override
	public void setHeaderData(List<HeaderData> data) {
		logger.log(Level.INFO, "RoomPlanView().setHeaderData()");
		roomPlanGrid.getCellFormatter().setWidth(0, 0, "150px");
		roomPlanGrid.setWidget(0, 0, new RoomTile("Hello"));
		for (int i = 0; i < data.size(); i++) {
			roomPlanGrid.setWidget(0, i + 1,
					new DayTile(data.get(i).getUnassined(), data.get(i).getDate(), data.get(i).getOccupancy()));
		}
	}

	@Override
	public void setRoomData(List<RoomTypeRowData> data) {
		logger.log(Level.INFO, "RoomPlanView().setRoomData()");
		int l = 0;
		for (RoomTypeRowData roomTypeRow : data) {
			logger.log(Level.INFO, "RoomPlanView().setRoomData()->roomTypeRow.getType()=" + roomTypeRow.getType());
			bookingGrid.getCellFormatter().setWidth(l, 0, "150px");
			bookingGrid.getCellFormatter().setHeight(l, 0, "50px");
			bookingGrid.setWidget(l, 0, new RoomTypeTile(roomTypeRow.getType() + " - " + roomTypeRow.getDescription()));
			for (int j = 0; j < roomTypeRow.getDaySummary().size(); j++) {
				bookingGrid.setWidget(l, j + 1, new RoomTypeDayTile(roomTypeRow.getDaySummary().get(j).getAvailable(),
						roomTypeRow.getDaySummary().get(j).getPrice()));
			}
			l++;
			for (RoomCellData roomCell : roomTypeRow.getRooms()) {
				logger.log(Level.INFO, "RoomPlanView().setRoomData()->roomCell.getCode()=" + roomCell.getCode());
				bookingGrid.getCellFormatter().setWidth(l, 0, "150px");
				bookingGrid.getCellFormatter().setHeight(l, 0, "30px");
				bookingGrid.setWidget(l, 0, new RoomTile(roomCell.getCode() + " - " + roomCell.getDescription()));
				l++;
			}
		}
/*		
		int bcat = bookingCanvas.getAbsoluteTop();
		logger.log(Level.INFO, "RoomPlanView().setRoomData()->bcat=" + bcat);
		String bct = bookingCanvas.getElement().getStyle().getTop();
		logger.log(Level.INFO, "RoomPlanView().setRoomData()->bct=" + bct);
		double bgh = bookingGrid.getOffsetHeight();
		logger.log(Level.INFO, "RoomPlanView().setRoomData()->bgh=" + bgh);
		bookingCanvas.getElement().getStyle().setTop(12-bgh, Unit.PX);
*/
		BookingWidget bw = new BookingWidget();
		bw.setTop(50);
		bw.setLeft(150);
		bw.setArrowColor(bookingStyle.red_arrow());
		bookingCanvas.add(bw);

		BookingWidget bw2 = new BookingWidget();
		bw2.setTop(80);
		bw2.setLeft(150);
		bw2.setArrowColor(bookingStyle.blue_arrow());
		bookingCanvas.add(bw2);
	}

}
