/**
 * 
 */
package io.crs.hsys.client.kip.filter.tasks;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.kip.filter.AppUserFilter;
import io.crs.hsys.client.kip.filter.RoomStatusFilter;
import io.crs.hsys.client.kip.filter.SwitchFilter;
import io.crs.hsys.client.kip.filter.TaskStatusFilter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.cnst.TaskStatus;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class TasksFilterView extends AbstractFilterView implements TasksFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(TasksFilterView.class.getName());

	private SwitchFilter reportedTasksFilter;
	private AppUserFilter reporterFilter;
	private SwitchFilter assignedTasksFilter;
	private AppUserFilter assigneeFilter;
	private TaskStatusFilter taskStatusFilter;
	private RoomStatusFilter roomStatusFilter;
	private MaterialComboBox<TaskKind> taskKindComboBox;
	private MaterialTextBox roomNumberField;
	private MaterialComboBox<RoomTypeDtor> roomTypeComboBox;
	private MaterialTextBox floorField;
	private String currentUserKey;

	private final EventBus eventBus;
	private final KipMessages i18n;
	private final CoreConstants i18nCoreCnst;

	@Inject
	Provider<SwitchFilter> switchFilterProvider;

	@Inject
	Provider<AppUserFilter> appUserFilterProvider;

	@Inject
	Provider<TaskStatusFilter> taskStatusFilterProvider;

	@Inject
	Provider<RoomStatusFilter> roomStatusFilterProvider;

	@Inject
	TasksFilterView(EventBus eventBus, CoreMessages i18nCore, KipMessages i18n, CoreConstants i18nCoreCnst) {
		super(i18nCore);
		logger.info("TasksFilterView()");
		this.eventBus = eventBus;
		this.i18n = i18n;
		this.i18nCoreCnst = i18nCoreCnst;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		initReportedTasksFilter();
		initReporterFilter();
		initAssignedTasksFilter();
		initAssigneeFilter();
		initTaskStatusPanel();
		/*
		 * ownedTasks = new MaterialSwitch(); ownedTasks.setOnLabel("Owned");
		 * ownedTasks.addValueChangeHandler(e -> { if (e.getValue()) {
		 * ownerFilter.setItemKey(currentUserKey); ownerFilter.setEnabled(false); } else
		 * { ownerFilter.unselect(); ownerFilter.setEnabled(true); } });
		 */

		roomStatusFilter = roomStatusFilterProvider.get();
		roomStatusFilter.setChipPanel(collapsibleHeader);
		roomStatusFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
//		roomStatusFilter.setFilterLabel(i18nCore.quotationFilterCodeLabel());

//		taskStatusPanel = new MaterialPanel();
//		cleaningStatusPanel = new MaterialPanel();
		taskKindComboBox = new MaterialComboBox<TaskKind>();
		roomNumberField = new MaterialTextBox();
		roomTypeComboBox = new MaterialComboBox<RoomTypeDtor>();
		floorField = new MaterialTextBox();

//		initTaskStatusPanel();
//		initCleaningStatusPanel();
		initTaskKindFilter();
		initRoomNumberFilter();
		initRoomTypeFilter();
		initFloorFilter();
	}

	private void initReportedTasksFilter() {
		reportedTasksFilter = switchFilterProvider.get();
		reportedTasksFilter.setChipLabel("Sajátjaim");
		reportedTasksFilter.setChipPanel(collapsibleHeader);
		reportedTasksFilter.setFilterOnLabel("Sajátjaim");
		reportedTasksFilter.addValueChangeHandler(e -> {
			if (e.getValue()) {
				reporterFilter.setChipEnabled(false);
				reporterFilter.setItemKey(currentUserKey);
				reporterFilter.setEnabled(false);
			} else {
				reporterFilter.setChipEnabled(true);
				reporterFilter.unselect();
				reporterFilter.setEnabled(true);
			}
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initReporterFilter() {
		reporterFilter = appUserFilterProvider.get();
//		ownerFilter.setChipLabel("Owner");
		reporterFilter.setChipPanel(collapsibleHeader);
		reporterFilter.setFilterLabel("Létrehozta");
		reporterFilter.setMarginLeft(10);
		reporterFilter.setMarginRight(10);
//		ownerFilter.setChipLetter("L");
//		ownerFilter.setChipLetterColor(Color.WHITE);
//		ownerFilter.setChipLetterBackgroundColor(Color.BLUE);
		reporterFilter.setChipIconType(IconType.RECORD_VOICE_OVER);
		reporterFilter.setChipIconPosition(IconPosition.LEFT);
		reporterFilter.setChipIconFontSize(20d, Unit.PX);
		reporterFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
		reporterFilter.addRemoveItemHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initAssignedTasksFilter() {
		assignedTasksFilter = switchFilterProvider.get();
		assignedTasksFilter.setChipLabel("Teendőim");
		assignedTasksFilter.setChipPanel(collapsibleHeader);
		assignedTasksFilter.setFilterOnLabel("Teendőim");
		assignedTasksFilter.addValueChangeHandler(e -> {
			if (e.getValue()) {
				assigneeFilter.setChipEnabled(false);
				assigneeFilter.setItemKey(currentUserKey);
				assigneeFilter.setEnabled(false);
			} else {
				assigneeFilter.setChipEnabled(true);
				assigneeFilter.unselect();
				assigneeFilter.setEnabled(true);
			}
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initAssigneeFilter() {
		assigneeFilter = appUserFilterProvider.get();
//		assignedFilter.setChipLabel("Assigned to");
		assigneeFilter.setChipPanel(collapsibleHeader);
		assigneeFilter.setFilterLabel("Felelős");
		assigneeFilter.setMarginLeft(10);
		assigneeFilter.setMarginRight(10);
//		assignedFilter.setChipLetter("F");
//		assignedFilter.setChipLetterColor(Color.WHITE);
//		assignedFilter.setChipLetterBackgroundColor(Color.RED);
		assigneeFilter.setChipIconType(IconType.ASSIGNMENT_IND);
		assigneeFilter.setChipIconPosition(IconPosition.LEFT);
		assigneeFilter.setChipIconFontSize(20d, Unit.PX);
		assigneeFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
		assigneeFilter.addRemoveItemHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initTaskStatusPanel() {
		taskStatusFilter = taskStatusFilterProvider.get();
		taskStatusFilter.setChipPanel(collapsibleHeader);
		taskStatusFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
	}

	/*
	 * private void initCleaningStatusPanel() { Label title = new
	 * Label(i18n.roomStatusFilterRoomStatusTitle());
	 * title.addStyleName("dataGroupTitle"); cleaningStatusPanel.add(title);
	 * 
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomDirty());
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomClean());
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomInspected());
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOoo());
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOos());
	 * initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomShow()); }
	 * 
	 * private void initCheckBox(MaterialPanel cleaningStatusPanel, String title) {
	 * MaterialChip chip = new MaterialChip(); chip.setText(title);
	 * chip.setVisible(false); collapsibleHeader.insert(chip, 1);
	 * 
	 * MaterialCheckBox checkBox = new MaterialCheckBox(); checkBox.setText(title);
	 * checkBox.setMarginTop(10);
	 * 
	 * checkBox.addValueChangeHandler(e -> { chip.setVisible(e.getValue());
	 * getUiHandlers().filterChange(); });
	 * 
	 * cleaningStatusPanel.add(checkBox); }
	 */
	private void initTaskKindFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		taskKindComboBox.setMarginTop(14);
		taskKindComboBox.setLabel(i18nCore.taskGroupFilterTaskKindLabel());
		taskKindComboBox.setPlaceholder(i18nCore.taskGroupFilterTaskKindPlaceholder());

		taskKindComboBox.addSelectionHandler(e -> {
			setTaskKindChip(chip, e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setTaskKindChip(MaterialChip chip, TaskKind taskKind) {
		Boolean visible = ((taskKind != null) && (!taskKind.equals(TaskKind.TK_ALL)));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18nCoreCnst.taskKindMap().get(taskKind.toString()));
	}

	private void initRoomNumberFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		roomNumberField.setMarginTop(14);
		roomNumberField.setLabel(i18n.roomStatusFilterRoomNumberLabel());
		roomNumberField.setPlaceholder(i18n.roomStatusFilterRoomNumberPlaceholder());

		roomNumberField.addValueChangeHandler(e -> {
			setRoomNumberChip(chip, e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setRoomNumberChip(MaterialChip chip, String roomNumber) {
		Boolean visible = ((roomNumber != null) && (!roomNumber.isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18n.roomStatusFilterRoomNumberChip(roomNumber));
	}

	private void initRoomTypeFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

//		roomTypeComboBox.setMarginTop(30);
		roomTypeComboBox.setLabel(i18n.roomStatusFilterRoomTypeLabel());
		roomTypeComboBox.setPlaceholder(i18n.roomStatusFilterRoomTypePlaceholder());

		roomTypeComboBox.addSelectionHandler(e -> {
			setRoomTypeChip(chip, e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setRoomTypeChip(MaterialChip chip, RoomTypeDtor roomType) {
		logger.info("RoomStatusFilterView2().setRoomTypeChip()");
		Boolean visible = ((roomType != null) && (roomType.getCode() != null) && (!roomType.getCode().isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(roomType.getCode());
	}

	private void initFloorFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		floorField.setLabel(i18n.roomStatusFilterRoomFloorLabel());
		floorField.setPlaceholder(i18n.roomStatusFilterRoomFloorPlaceholder());

		floorField.addValueChangeHandler(e -> {
			setFloorChip(chip, e.getValue());
			getUiHandlers().filterChange();
		});
	}

	private void setFloorChip(MaterialChip chip, String floorNumber) {
		Boolean visible = ((floorNumber != null) && (!floorNumber.isEmpty()));
		chip.setVisible(visible);
		if (visible)
			chip.setText(i18n.roomStatusFilterRoomFloorChip(floorNumber));
	}

	@Override
	protected void createLayout() {
		MaterialPanel panel1 = new MaterialPanel();
		panel1.setPadding(0);
		panel1.setGrid("s6 m4");
		panel1.add(reportedTasksFilter);
		panel1.add(reporterFilter);
		panel1.add(assignedTasksFilter);
		panel1.add(assigneeFilter);
		controlPanel.add(panel1);

		createTaskStatusLayout();

		createRoomStatusLayout();
		setTaskKindLayout();
		roomNumberField.setGrid("s6 m4");
		controlPanel.add(roomNumberField);

		floorField.setGrid("s6 m4");
		controlPanel.add(floorField);

		roomTypeComboBox.setGrid("s6 m4");
		controlPanel.add(roomTypeComboBox);
	}

	private void createTaskStatusLayout() {
		taskStatusFilter.setGrid("s6 m4");
//		taskStatusPanel.setBorderLeft("3px solid " + BlueThemeColors.C_PRIMARY);
//		taskStatusFilter.addStyleName("dataGroupBox");
		controlPanel.add(taskStatusFilter);
	}

	private void createRoomStatusLayout() {
		roomStatusFilter.setGrid("s6 m4");
//		cleaningStatusPanel.setBorderLeft("3px solid " + BlueThemeColors.C_PRIMARY);
//		cleaningStatusPanel.addStyleName("dataGroupBox");
		controlPanel.add(roomStatusFilter);
	}

	protected void setTaskKindLayout() {
		taskKindComboBox.setGrid("s6 m4");
		controlPanel.add(taskKindComboBox);
	};

	@Override
	public void setTaskKindData(List<TaskKind> taskKindData) {
		taskKindComboBox.clear();
		for (TaskKind tk : taskKindData)
			taskKindComboBox.addItem(i18nCoreCnst.taskKindMap().get(tk.toString()), tk);
	}

	@Override
	public TaskKind getSelectedTaskKind() {
		return taskKindComboBox.getSelectedValue().get(0);
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> hotelData) {
		roomTypeComboBox.clear();
		roomTypeComboBox.addItem(i18n.roomStatusFilterRoomTypeAll(), new RoomTypeDtor());
		for (RoomTypeDtor hd : hotelData)
			roomTypeComboBox.addItem(hd.getCode(), hd);
	}

	@Override
	public RoomTypeDtor getSelectedRoomType() {
		return roomTypeComboBox.getSelectedValue().get(0);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TaskStatus> getSelectedTaskStatuses() {
		return taskStatusFilter.getSelectedTaskStatuses();
	}

	@Override
	public void setAppUserData(List<AppUserDtor> data) {
		reporterFilter.setComboBoxData(data);
		assigneeFilter.setComboBoxData(data);

		reporterFilter.setItemKey(currentUserKey);
		assigneeFilter.setItemKey(currentUserKey);
	}

	@Override
	public void setCurrentUserKey(String webSafeKey) {
		this.currentUserKey = webSafeKey;
	}

	@Override
	public String getSelectedReporterKey() {
		return reporterFilter.getSelectedDataKey();
	}

	@Override
	public String getSelectedAssigneeKey() {
		return assigneeFilter.getSelectedDataKey();
	}
}
