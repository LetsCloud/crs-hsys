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

import io.crs.hsys.client.core.filter.TextFilter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.kip.filter.AppUserFilter;
import io.crs.hsys.client.kip.filter.DateFilter;
import io.crs.hsys.client.kip.filter.RoomStatusFilter;
import io.crs.hsys.client.kip.filter.SwitchFilter;
import io.crs.hsys.client.kip.filter.TaskKindFilter;
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
	private TaskKindFilter taskKindFilter;
//	private MaterialComboBox<TaskKind> taskKindComboBox;
	private MaterialComboBox<RoomTypeDtor> roomTypeComboBox;
	private TextFilter roomFilter, floorFilter;
	private DateFilter fromDateFilter;
	private DateFilter toDateFilter;

	private String currentUserKey;

	private final EventBus eventBus;
	private final KipMessages i18n;

	@Inject
	Provider<SwitchFilter> switchFilterProvider;

	@Inject
	Provider<AppUserFilter> appUserFilterProvider;

	@Inject
	Provider<TaskStatusFilter> taskStatusFilterProvider;

	@Inject
	Provider<RoomStatusFilter> roomStatusFilterProvider;

	@Inject
	Provider<DateFilter> dateFilterProvider;

	@Inject
	Provider<TextFilter> textFilterProvider;

	@Inject
	Provider<TaskKindFilter> tasKindFilterProvider;

	@Inject
	TasksFilterView(EventBus eventBus, CoreMessages i18nCore, KipMessages i18n) {
		super(i18nCore);
		logger.info("TasksFilterView()");
		this.eventBus = eventBus;
		this.i18n = i18n;
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
		initRoomStatusPanel();
		initTaskKindFilter();
		initRoomNumberFilter();
		initRoomTypeFilter();
		initFloorFilter();
		initFromDateFilter();
		initToDateFilter();
	}

	private void initReportedTasksFilter() {
		reportedTasksFilter = switchFilterProvider.get();
		reportedTasksFilter.setChipLabel("Saját");
		reportedTasksFilter.setChipPanel(collapsibleHeader);
		reportedTasksFilter.setFilterOnLabel("Saját");
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

	private void initRoomStatusPanel() {
		roomStatusFilter = roomStatusFilterProvider.get();
		roomStatusFilter.setChipPanel(collapsibleHeader);
		roomStatusFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
//		roomStatusFilter.setFilterLabel(i18nCore.quotationFilterCodeLabel());
	}

	private void initTaskKindFilter() {
		taskKindFilter = tasKindFilterProvider.get();
		taskKindFilter.setMarginTop(14);
		taskKindFilter.setFilterLabel(i18nCore.taskGroupFilterTaskKindLabel());
		taskKindFilter.setFilterPlaceholder(i18nCore.taskGroupFilterTaskKindPlaceholder());
		taskKindFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initRoomNumberFilter() {
		roomFilter = textFilterProvider.get();
		roomFilter.setChipPanel(collapsibleHeader);
		roomFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
		roomFilter.setFilterLabel(i18n.roomStatusFilterRoomNumberLabel());
		roomFilter.setFilterPlaceholder(i18n.roomStatusFilterRoomNumberPlaceholder());
		roomFilter.setFilterMarginTop(14);
		roomFilter.setFilterHeight(45, Unit.PX);
		roomFilter.addValueChangeHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initRoomTypeFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		roomTypeComboBox = new MaterialComboBox<RoomTypeDtor>();
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
		floorFilter = textFilterProvider.get();
		floorFilter.setChipPanel(collapsibleHeader);
		floorFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
		floorFilter.setFilterLabel(i18n.roomStatusFilterRoomFloorLabel());
		floorFilter.setFilterPlaceholder(i18n.roomStatusFilterRoomFloorPlaceholder());
		floorFilter.setFilterMarginTop(14);
		floorFilter.setFilterHeight(45, Unit.PX);
		floorFilter.addValueChangeHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initFromDateFilter() {
		fromDateFilter = dateFilterProvider.get();
		fromDateFilter.setChipPanel(collapsibleHeader);
		fromDateFilter.setChipLabel("Mettől:");
		fromDateFilter.setFilterLabel("Mettől");
		fromDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initToDateFilter() {
		toDateFilter = dateFilterProvider.get();
		toDateFilter.setChipPanel(collapsibleHeader);
		toDateFilter.setChipLabel("Mettől:");
		toDateFilter.setFilterLabel("Meddig");
		toDateFilter.setFilterHeight(45, Unit.PX);
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

		roomFilter.setGrid("s6 m4");
		controlPanel.add(roomFilter);

		floorFilter.setGrid("s6 m4");
		controlPanel.add(floorFilter);

		roomTypeComboBox.setGrid("s6 m4");
		controlPanel.add(roomTypeComboBox);

		fromDateFilter.setGrid("s6 m4");
		controlPanel.add(fromDateFilter);

		toDateFilter.setGrid("s6 m4");
		controlPanel.add(toDateFilter);

		setTaskKindLayout();
	}

	private void createTaskStatusLayout() {
		taskStatusFilter.setGrid("s6 m4");
		controlPanel.add(taskStatusFilter);
	}

	private void createRoomStatusLayout() {
		roomStatusFilter.setGrid("s6 m4");
		controlPanel.add(roomStatusFilter);
	}

	protected void setTaskKindLayout() {
		taskKindFilter.setGrid("s6 m4");
		controlPanel.add(taskKindFilter);
	};

	@Override
	public void setTaskKindData(List<TaskKind> data) {
		taskKindFilter.setComboBoxData(data);
	}

	@Override
	public TaskKind getSelectedTaskKind() {
		return taskKindFilter.getSelectedItem();
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
