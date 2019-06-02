/**
 * 
 */
package io.crs.hsys.client.fro.roomplan.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.RoomPlanViewDropController;
import com.allen_sauer.gwt.dnd.client.drop.RoomPlanViewPickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>
 * A CalendarView that displays appointments for a given month. The Month is
 * displayed in a grid-style view where cells represents days, columns
 * represents days of the week (i.e. Monday, Tuesday, etc.) and rows represent a
 * full week (Sunday through Saturday).
 * <p/>
 * <p/>
 * <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.gwt-cal-MonthView { }</li>
 * <li>.dayCell { cell that represents a day }</li>
 * <li>.dayCell-today { cell that represents today }</li>
 * <li>.dayCell-disabled { cell's day falls outside the month }</li>
 * <li>.dayCell-today-disabled { cell represents today, falls outside the month
 * }</li>
 * <li>.dayCellLabel { header for the cell }</li>
 * <li>.dayCellLabel-today { cell represents today }</li>
 * <li>.dayCellLabel-disabled { cell's day falls outside the month }</li>
 * <li>.dayCellLabel-today-disabled { cell represents today, falls outside the
 * month }</li>
 * <li>.weekDayLabel { label for the days of the week }</li>
 * </ul>
 *
 * @author Brad Rydzewski
 * @since 0.9.0
 */
public class RoomPlanView extends AbstractRoomPlanView {

	private List<Widget> dragControlledWidgets = new LinkedList<Widget>();

	public static final Comparator<Booking> BOOKING_COMPARATOR = new Comparator<Booking>() {

		public int compare(Booking a1, Booking a2) {
			int compare = Boolean.valueOf(a2.isMultiDay()).compareTo(a1.isMultiDay());

			if (compare == 0) {
				compare = a1.getStart().compareTo(a2.getStart());
			}

			if (compare == 0) {
				compare = a2.getEnd().compareTo(a1.getEnd());
			}

			return compare;
		}
	};

	private static final int DAYS_IN_A_WEEK = 7;
	private final static String MONTH_VIEW = "gwt-cal-MonthView";
	private final static String CANVAS_STYLE = "canvas";
	private final static String GRID_STYLE = "grid";
	private final static String CELL_STYLE = "dayCell";
	private final static String MORE_LABEL_STYLE = "moreAppointments";
	private final static String CELL_HEADER_STYLE = "dayCellLabel";
	private final static String WEEKDAY_LABEL_STYLE = "weekDayLabel";
	private final static String WEEKNUMBER_LABEL_STYLE = "weekNumberLabel";

	/**
	 * List of appointment panels drawn on the month view canvas.
	 */
	private ArrayList<BookingWidget> bookingWidgets = new ArrayList<BookingWidget>();

	/**
	 * All appointments are placed on this canvas and arranged.
	 */
	private AbsolutePanel bookingCanvas = new AbsolutePanel();

	/**
	 * All "+ n more" Labels, mapped to its cell in the MonthView Grid.
	 */
	private HashMap<Element, Integer> moreLabels = new HashMap<Element, Integer>();
	private ArrayList<Label> dayLabels = new ArrayList<Label>();
	private ArrayList<Widget> dayPanels = new ArrayList<Widget>();

	/**
	 * The first date displayed on the MonthView (1st cell.) This date is not
	 * necessarily the first date of the month as the month view will sometimes
	 * display days from the adjacent months because of the number of days fitting
	 * in the visible grid.
	 */
	private Date firstDateDisplayed;

	/**
	 * Grid that makes up the days and weeks of the MonthView.
	 */
	private FlexTable roomPlanGrid = new FlexTable();

	/**
	 * The number of rows required to display the entire month in grid format.
	 * Although most months span a total of five weeks, there are some months that
	 * span six weeks.
	 */
	private int numberOfRooms = 5;

	/**
	 * List of <code>AppointmentWidget</code>s that are associated to the currently
	 * selected <code>Booking</code> appointment.
	 */
	private ArrayList<BookingWidget> selectedAppointmentWidgets = new ArrayList<BookingWidget>();

	private PickupDragController dragController = null;

	private RoomPlanViewDropController dropController = null;

	private MonthViewStyleManager styleManager = GWT.create(MonthViewStyleManager.class);
	
	/**
	 * This method is called when the MonthView is attached to the Calendar and
	 * displayed. This is where all components are configured and added to the
	 * RootPanel.
	 */
	public void attach(RoomPlanWidget widget) {
		super.attach(widget);

		roomPlanWidget.addToRootPanel(roomPlanGrid);
		roomPlanGrid.setCellPadding(0);
		roomPlanGrid.setBorderWidth(0);
		roomPlanGrid.setCellSpacing(0);
		roomPlanGrid.setStyleName(GRID_STYLE);

		roomPlanWidget.addToRootPanel(bookingCanvas);
		bookingCanvas.setStyleName(CANVAS_STYLE);

		selectedAppointmentWidgets.clear();

		if (dragController == null) {
			dragController = new RoomPlanViewPickupDragController(bookingCanvas, true);
			dragController.addDragHandler(new DragHandler() {

				public void onDragEnd(DragEndEvent event) {
					Booking appt = ((BookingWidget) event.getContext().draggable).getAppointment();

					roomPlanWidget.setCommittedAppointment(appt);
					roomPlanWidget.fireUpdateEvent(appt);
				}

				public void onDragStart(DragStartEvent event) {
					Booking appt = ((BookingWidget) event.getContext().draggable).getAppointment();
					roomPlanWidget.setRollbackAppointment(appt.clone());
				}

				public void onPreviewDragEnd(DragEndEvent event) throws VetoDragException {
					// do nothing
				}

				public void onPreviewDragStart(DragStartEvent event) throws VetoDragException {
					// do nothing
				}
			});
		}

		/*
		 * Need to re-set appointmentCanvas to position:absolute because gwt-dnd will
		 * set it to relative, but then the layout gets f***ed up
		 */
		// DOM.setStyleAttribute(appointmentCanvas.getElement(), "position",
		// "absolute");
		bookingCanvas.getElement().getStyle().setPosition(Position.ABSOLUTE);

		dragController.setBehaviorDragStartSensitivity(5);
		dragController.setBehaviorDragProxy(true);

		// instantiate our drop controller
		dropController = new RoomPlanViewDropController(bookingCanvas, roomPlanGrid);
		dragController.registerDropController(dropController);

	}

	/**
	 * Performs a Layout and arranges all appointments on the MonthView's
	 * appointment canvas.
	 */
	@Override
	public void doLayout() {
		// Clear all existing appointments

		for (Widget dragControlledWidget : dragControlledWidgets) {
			dragController.makeNotDraggable(dragControlledWidget);
		}
		dragControlledWidgets.clear();

		bookingCanvas.clear();
		roomPlanGrid.clear();
		bookingWidgets.clear();
		moreLabels.clear();
		dayLabels.clear();
		dayPanels.clear();
		selectedAppointmentWidgets.clear();
		while (roomPlanGrid.getRowCount() > 0) {
			roomPlanGrid.removeRow(0);
		}

		// Rebuild the month grid
		buildRoomPlanGrid();

		// (Re)calculate some variables
		calculateCellHeight();
		calculateCellBookings();

		// set variables needed by the drop controller
		// monthViewDropController.setDayHeaderHeight(calculatedDayHeaderHeight);
		dropController.setDaysPerWeek(DAYS_IN_A_WEEK);
		// monthViewDropController.setWeekdayHeaderHeight(calculatedWeekDayHeaderHeight);
		dropController.setWeeksPerMonth(numberOfRooms);
		dropController.setFirstDateDisplayed(firstDateDisplayed);

		// Sort the appointments
		// TODO: don't re-sort the appointment unless necessary
		Collections.sort(roomPlanWidget.getAppointments(), BOOKING_COMPARATOR);
		// Distribute appointments
		PeriodLayoutDescription periodLayoutDescription = new PeriodLayoutDescription(firstDateDisplayed,
				numberOfRooms, roomPlanWidget.getAppointments(), calculatedCellAppointments - 1);

		int dayIndex = 0;
		for (int row = 0; row < roomPlanGrid.getRowCount() - 1; row++) {
			for (int col = 0; col < DAYS_IN_A_WEEK; col++) {
				Widget lbl = dayPanels.get(dayIndex);
				placeDayLabelInGrid(lbl, col, row);
				dayIndex++;
			}
		}

		// Get the layouts for each week in the month
		RoomLayoutDescription[] weeks = periodLayoutDescription.getWeekDescriptions();
		for (int weekOfMonth = 0; weekOfMonth < weeks.length && weekOfMonth < numberOfRooms; weekOfMonth++) {
			RoomLayoutDescription weekDescription = weeks[weekOfMonth];
			if (weekDescription != null) {
				layOnTopOfTheWeekHangingAppointments(weekDescription, weekOfMonth);
				layOnWeekDaysAppointments(weekDescription, weekOfMonth);
			}
		}
	}

	private void layOnTopOfTheWeekHangingAppointments(RoomLayoutDescription weekDescription, int weekOfMonth) {
		BookingStackingManager weekTopElements = weekDescription.getTopAppointmentsManager();
		for (int layer = 0; layer < calculatedCellAppointments; layer++) {

			ArrayList<BookingLayoutDescription> descriptionsInLayer = weekTopElements.getDescriptionsInLayer(layer);

			if (descriptionsInLayer == null) {
				break;
			}

			for (BookingLayoutDescription weekTopElement : descriptionsInLayer) {
				layOnAppointment(weekTopElement.getBooking(), weekTopElement.getFromDayCell(),
						weekTopElement.getToDayCell(), weekOfMonth, layer);
			}
		}
	}

	private void layOnWeekDaysAppointments(RoomLayoutDescription room, int weekOfMonth) {

		BookingStackingManager topAppointmentManager = room.getTopAppointmentsManager();

		for (int dayOfWeek = 0; dayOfWeek < DAYS_IN_A_WEEK; dayOfWeek++) {
			DayCellLayoutDescription dayAppointments = room.getDayLayoutDescription(dayOfWeek);

			int bookingLayer = topAppointmentManager.lowestLayerIndex(dayOfWeek);

			if (dayAppointments != null) {
				int count = dayAppointments.getAppointments().size();
				for (int i = 0; i < count; i++) {
					Booking appointment = dayAppointments.getAppointments().get(i);
					bookingLayer = topAppointmentManager.nextLowestLayerIndex(dayOfWeek, bookingLayer);
					if (bookingLayer > calculatedCellAppointments - 1) {
						int remaining = count + topAppointmentManager.multidayAppointmentsOverLimitOn(dayOfWeek) - i;
						if (remaining == 1) {
							layOnAppointment(appointment, dayOfWeek, dayOfWeek, weekOfMonth, bookingLayer);
						} else {
							layOnNMoreLabel(remaining, dayOfWeek, weekOfMonth);
						}
						break;
					}
					layOnAppointment(appointment, dayOfWeek, dayOfWeek, weekOfMonth, bookingLayer);
					bookingLayer++;
				}
			} else if (topAppointmentManager.multidayAppointmentsOverLimitOn(dayOfWeek) > 0) {
				layOnNMoreLabel(topAppointmentManager.multidayAppointmentsOverLimitOn(dayOfWeek), dayOfWeek,
						weekOfMonth);
			}
		}
	}

	private void layOnNMoreLabel(int moreCount, int dayOfWeek, int weekOfMonth) {
		Label more = new Label("More");
		more.setStyleName(MORE_LABEL_STYLE);
		placeItemInGrid(more, dayOfWeek, dayOfWeek, weekOfMonth, calculatedCellAppointments);
		bookingCanvas.add(more);
		moreLabels.put(more.getElement(), (dayOfWeek) + (weekOfMonth * 7));
	}

	private void layOnAppointment(Booking appointment, int colStart, int colEnd, int row, int cellPosition) {
		BookingWidget panel = new BookingWidget(appointment);

		placeItemInGrid(panel, colStart, colEnd, row, cellPosition);

		boolean selected = roomPlanWidget.isTheSelectedAppointment(appointment);
		styleManager.applyStyle(panel, selected);

		if (roomPlanWidget.getSettings().isEnableDragDrop() && !appointment.isReadOnly()) {
			dragController.makeDraggable(panel);
			dragControlledWidgets.add(panel);
		}

		if (selected)
			selectedAppointmentWidgets.add(panel);

		bookingWidgets.add(panel);
		bookingCanvas.add(panel);
	}

	/**
	 * Gets the Month View's primary style name.
	 */
	public String getStyleName() {
		return MONTH_VIEW;
	}

	/**
	 * Handles the DoubleClick event to determine if an Booking has been selected.
	 * If an appointment has been double clicked the OpenEvent will get fired for
	 * that appointment.
	 */
	public void onDoubleClick(Element clickedElement, Event event) {
		if (clickedElement.equals(bookingCanvas.getElement())) {
			if (roomPlanWidget.getSettings().getTimeBlockClickNumber() == RoomPlanSettings.Click.Double) {
				dayClicked(event);
			}
		} else {
			ArrayList<BookingWidget> list = findAppointmentWidgetsByElement(clickedElement);
			if (!list.isEmpty()) {
				roomPlanWidget.fireOpenEvent(list.get(0).getAppointment());
			}
		}
	}

	/**
	 * Handles the a single click to determine if an appointment has been selected.
	 * If an appointment is clicked it's selected status will be set to true and a
	 * SelectionEvent will be fired.
	 */
	@Override
	public void onSingleClick(Element clickedElement, Event event) {
		if (clickedElement.equals(bookingCanvas.getElement())) {
			if (roomPlanWidget.getSettings().getTimeBlockClickNumber() == RoomPlanSettings.Click.Single) {
				dayClicked(event);
			}
		} else {
			Booking appointment = findAppointmentByElement(clickedElement);
			if (appointment != null) {
				selectAppointment(appointment);
			} else {
				// else, lets see if a "+ n more" label was clicked
				if (moreLabels.containsKey(clickedElement)) {
					roomPlanWidget.fireDateRequestEvent(cellDate(moreLabels.get(clickedElement)), clickedElement);
				}
			}
		}
	}

	public void onMouseOver(Element element, Event event) {
		Booking appointment = findAppointmentByElement(element);
		roomPlanWidget.fireMouseOverEvent(appointment, element);
	}

	/**
	 * Returns the date corresponding to the <code>cell</code> (as if the month view
	 * grid was a big linear sequence of cells) in the month view grid.
	 * 
	 * @param cell The cell number in the month view grid
	 * @return The date that corresponds to the given <code>cell</code>
	 */
	private Date cellDate(int cell) {
		return DateUtils.shiftDate(firstDateDisplayed, cell);
	}

	private void dayClicked(Event event) {
		int y = event.getClientY() + Window.getScrollTop() - DOM.getAbsoluteTop(bookingCanvas.getElement());
		int x = event.getClientX() + Window.getScrollLeft() - DOM.getAbsoluteLeft(bookingCanvas.getElement());

		int row = (int) Math.floor(y / (bookingCanvas.getOffsetHeight() / numberOfRooms));
		int col = (int) Math.floor(x / (bookingCanvas.getOffsetWidth() / DAYS_IN_A_WEEK));
		roomPlanWidget.fireTimeBlockClickEvent(cellDate(row * DAYS_IN_A_WEEK + col));
	}

	private ArrayList<BookingWidget> findAppointmentWidgetsByElement(Element element) {
		return findAppointmentWidgets(findAppointmentByElement(element));
	}

	/**
	 * Builds and formats the Calendar Grid. No appointments are included when
	 * building the grid.
	 */
	@SuppressWarnings("deprecation")
	private void buildRoomPlanGrid() {
		int firstDayOfWeek =1;
		int month = roomPlanWidget.getDate().getMonth();
		firstDateDisplayed = MonthViewDateUtils.firstDateShownInAMonthView(roomPlanWidget.getDate(), firstDayOfWeek);

		Date today = new Date();
		DateUtils.resetTime(today);

		/* Add the roomplan days heading */
		for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
//			roomPlanGrid.setText(0, i,
//					CalendarFormat.INSTANCE.getDayOfWeekAbbreviatedNames()[(i + firstDayOfWeek) % 7]);
			roomPlanGrid.setText(0, i, "A hét napja");
			roomPlanGrid.getCellFormatter().setVerticalAlignment(0, i, HasVerticalAlignment.ALIGN_TOP);
			roomPlanGrid.getCellFormatter().setStyleName(0, i, WEEKDAY_LABEL_STYLE);
		}
		
		Date date = (Date) firstDateDisplayed.clone();
		numberOfRooms = MonthViewDateUtils.monthViewRequiredRows(roomPlanWidget.getDate(), firstDayOfWeek);
		int weekNumber = DateUtils.calendarWeekIso(date);

		for (int roomPlanGridRowIndex = 1; roomPlanGridRowIndex <= numberOfRooms; roomPlanGridRowIndex++) {
			for (int dayOfWeekIndex = 0; dayOfWeekIndex < DAYS_IN_A_WEEK; dayOfWeekIndex++) {

				if (roomPlanGridRowIndex != 1 || dayOfWeekIndex != 0) {
					DateUtils.moveOneDayForward(date);
					weekNumber = DateUtils.calendarWeekIso(date);
				}

				configureDayInGrid(roomPlanGridRowIndex, dayOfWeekIndex, date, date.equals(today),
						date.getMonth() != month, weekNumber);
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
	private void configureDayInGrid(int row, int col, Date date, boolean isToday, boolean notInCurrentMonth,
			int weekNumber) {
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

		if (notInCurrentMonth) {
			headerStyle.append("-disabled");
		}

		label.setStyleName(headerStyle.toString());
		addDayClickHandler(label, (Date) date.clone());

		if (col == 0 && getSettings().isShowingWeekNumbers()) {
			Label weekLabel = new Label(String.valueOf(weekNumber));
			weekLabel.setStyleName(WEEKNUMBER_LABEL_STYLE);

			panel.add(weekLabel);
			panel.setCellWidth(weekLabel, "25px");
			DOM.setStyleAttribute(label.getElement(), "paddingLeft", "5px");
			addWeekClickHandler(weekLabel, (Date) date.clone());
		}
		panel.add(label);

		bookingCanvas.add(panel);
		dayLabels.add(label);
		dayPanels.add(panel);

		// monthCalendarGrid.setWidget(row, col, panel);
		roomPlanGrid.getCellFormatter().setVerticalAlignment(row, col, HasVerticalAlignment.ALIGN_TOP);
		roomPlanGrid.getCellFormatter().setStyleName(row, col, cellStyle.toString());
	}

	/**
	 * Returns the {@link Booking} indirectly associated to the passed
	 * <code>element</code>. Each Booking drawn on the CalendarView maps to a Widget
	 * and therefore an Element. This method attempts to find an Booking based on
	 * the provided Element. If no match is found a null value is returned.
	 *
	 * @param element Element to look up.
	 * @return Booking matching the element.
	 */
	private Booking findAppointmentByElement(Element element) {
		Booking appointmentAtElement = null;
		for (BookingWidget widget : bookingWidgets) {
			if (DOM.isOrHasChild(widget.getElement(), element)) {
				appointmentAtElement = widget.getAppointment();
				break;
			}
		}
		return appointmentAtElement;
	}

	/**
	 * Finds any related <code>AppointmentWidgets</code> associated to the passed
	 * Booking, <code>appt</code>.
	 *
	 * @param appt Booking to match.
	 * @return List of related AppointmentWidget objects.
	 */
	private ArrayList<BookingWidget> findAppointmentWidgets(Booking appt) {
		ArrayList<BookingWidget> appointmentWidgets = new ArrayList<BookingWidget>();
		if (appt != null) {
			for (BookingWidget widget : bookingWidgets) {
				if (widget.getAppointment().equals(appt)) {
					appointmentWidgets.add(widget);
				}
			}
		}
		return appointmentWidgets;
	}

	public void onDeleteKeyPressed() {
		if (roomPlanWidget.getSelectedAppointment() != null)
			roomPlanWidget.fireDeleteEvent(roomPlanWidget.getSelectedAppointment());
	}

	@Override
	public void onAppointmentSelected(Booking appt) {
		ArrayList<BookingWidget> clickedAppointmentWidgets = findAppointmentWidgets(appt);

		if (!clickedAppointmentWidgets.isEmpty()) {
			for (BookingWidget widget : selectedAppointmentWidgets) {
				// widget.removeStyleDependentName("selected");
				// DOM.setStyleAttribute(widget.getElement(),
				// "borderColor", widget.getAppointment().getAppointmentStyle().getBorder());
				styleManager.applyStyle(widget, false);
			}

			for (BookingWidget widget : clickedAppointmentWidgets) {
				// widget.addStyleDependentName("selected");
				// DOM.setStyleAttribute(widget.getElement(),
				// "borderColor", appt.getAppointmentStyle().getSelectedBorder());
				styleManager.applyStyle(widget, true);
			}

			selectedAppointmentWidgets.clear();
			selectedAppointmentWidgets = clickedAppointmentWidgets;
		}
	}

	/**
	 * Multiple calculated (&quot;cached&quot;) values reused during laying out the
	 * month view elements.
	 */

	private int calculatedWeekDayHeaderHeight;
	private int calculatedDayHeaderHeight;

	/**
	 * Maximum appointments per cell (day).
	 */
	private int calculatedCellAppointments;

	/**
	 * Height of each Cell (day), including the day's header.
	 */
	private float calculatedCellOffsetHeight;

	/**
	 * Height of each Cell (day), excluding the day's header.
	 */
	private float calculatedCellHeight;

	/**
	 * Calculates the height of each day cell in the Period grid. It excludes the
	 * height of each day's header, as well as the overall header that shows the
	 * weekday labels.
	 */
	private void calculateCellHeight() {

		int gridHeight = roomPlanGrid.getOffsetHeight();
		int weekdayRowHeight = roomPlanGrid.getRowFormatter().getElement(0).getOffsetHeight();
		int dayHeaderHeight = dayLabels.get(0).getOffsetHeight();

		calculatedCellOffsetHeight = (float) (gridHeight - weekdayRowHeight) / numberOfRooms;
		calculatedCellHeight = calculatedCellOffsetHeight - dayHeaderHeight;
		calculatedWeekDayHeaderHeight = weekdayRowHeight;
		calculatedDayHeaderHeight = dayHeaderHeight;
	}

	/**
	 * Calculates the maximum number of bookings that can be displayed in a
	 * given &quot;day cell&quot;.
	 */
	private void calculateCellBookings() {
		int paddingTop = appointmentPaddingTop();
		int height = bookingHeight();

		calculatedCellAppointments = (int) Math
				.floor((float) (calculatedCellHeight - paddingTop) / (float) (height + paddingTop)) - 1;
	}

	private static int appointmentPaddingTop() {
//		return 1 + (Math.abs(FormattingUtil.getBorderOffset()) * 3);
		return 0;
	}

	private static int bookingHeight() {
		// TODO: calculate appointment height dynamically
		return 20;
	}

	private void placeItemInGrid(Widget panel, int colStart, int colEnd, int row, int cellPosition) {
		int paddingTop = appointmentPaddingTop() + 3;
		int height = bookingHeight();

		float left = (float) colStart / (float) DAYS_IN_A_WEEK * 100f + .5f;

		float width = ((float) (colEnd - colStart + 1) / (float) DAYS_IN_A_WEEK) * 100f - 1f;

		float top = calculatedWeekDayHeaderHeight + (row * calculatedCellOffsetHeight) + calculatedDayHeaderHeight
				+ paddingTop + (cellPosition * (height + paddingTop));
//		 System.out.println( "\t" + calculatedWeekDayHeaderHeight + " + (" + row +
//		 " * " + calculatedCellOffsetHeight + ") + " +
//		 calculatedDayHeaderHeight + " + " + paddingTop + " + (" +
//		 cellPosition+"*("+height+"+"+paddingTop + "));");
		
//		DOM.setStyleAttribute(panel.getElement(), "position", "absolute");
//		DOM.setStyleAttribute(panel.getElement(), "top", top + "px");
//		DOM.setStyleAttribute(panel.getElement(), "left", left + "%");
//		DOM.setStyleAttribute(panel.getElement(), "width", width + "%");

		panel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		panel.getElement().getStyle().setTop(top, Unit.PX);
		panel.getElement().getStyle().setLeft(left, Unit.PCT);
		panel.getElement().getStyle().setWidth(width, Unit.PCT);
	}

	private void placeDayLabelInGrid(Widget panel, int col, int row) {
		int paddingTop = appointmentPaddingTop();

		float left = (float) col / (float) DAYS_IN_A_WEEK * 100f + .5f;

		float width = (1f / (float) DAYS_IN_A_WEEK) * 100f - 1f;

		float top = calculatedWeekDayHeaderHeight + (row * calculatedCellOffsetHeight) + paddingTop;
//		DOM.setStyleAttribute(panel.getElement(), "position", "absolute");
//		DOM.setStyleAttribute(panel.getElement(), "top", top + "px");
//		DOM.setStyleAttribute(panel.getElement(), "left", left + "%");
//		DOM.setStyleAttribute(panel.getElement(), "width", width + "%");

		panel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		panel.getElement().getStyle().setTop(top, Unit.PX);
		panel.getElement().getStyle().setLeft(left, Unit.PCT);
		panel.getElement().getStyle().setWidth(width, Unit.PCT);
	}
}