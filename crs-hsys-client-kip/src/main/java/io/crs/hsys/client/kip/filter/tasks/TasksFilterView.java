/**
 * 
 */
package io.crs.hsys.client.kip.filter.tasks;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.user.client.ui.Label;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.client.kip.filter.TaskStatusFilter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class TasksFilterView extends AbstractFilterView implements TasksFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(TasksFilterView.class.getName());

	private MaterialPanel cleaningStatusPanel;
	private TaskStatusFilter taskStatusFilter;
	private MaterialComboBox<TaskKind> taskKindComboBox;
	private MaterialTextBox roomNumberField;
	private MaterialComboBox<RoomTypeDtor> roomTypeComboBox;
	private MaterialTextBox floorField;

	private final KipMessages i18n;
	private final CoreConstants i18nCoreCnst;

	@Inject
	Provider<TaskStatusFilter> taskStatusFilterProvider;

	@Inject
	TasksFilterView(CoreMessages i18nCore, KipMessages i18n, CoreConstants i18nCoreCnst) {
		super(i18nCore);
		logger.info("TasksFilterView()");
		this.i18n = i18n;
		this.i18nCoreCnst = i18nCoreCnst;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		taskStatusFilter = taskStatusFilterProvider.get();
		taskStatusFilter.setChipPanel(collapsibleHeader);
		taskStatusFilter.setChipLabel(i18nCore.quotationFilterCodeChipLabel());
//		taskStatusFilter.setFilterLabel(i18nCore.quotationFilterCodeLabel());

//		taskStatusPanel = new MaterialPanel();
		cleaningStatusPanel = new MaterialPanel();
		taskKindComboBox = new MaterialComboBox<TaskKind>();
		roomNumberField = new MaterialTextBox();
		roomTypeComboBox = new MaterialComboBox<RoomTypeDtor>();
		floorField = new MaterialTextBox();

//		initTaskStatusPanel();
		initCleaningStatusPanel();
		initTaskKindFilter();
		initRoomNumberFilter();
		initRoomTypeFilter();
		initFloorFilter();
	}
/*
	private void initTaskStatusPanel() {
		Label title = new Label(i18n.tasksFilterTaskStatusTitle());
		title.addStyleName("dataGroupTitle");
		taskStatusPanel.add(title);

		initCheckBox(taskStatusPanel, i18n.tasksFilterStatusNotStarted());
		initCheckBox(taskStatusPanel, i18n.tasksFilterStatusInProgress());
		initCheckBox(taskStatusPanel, i18n.tasksFilterStatusDeffered());
		initCheckBox(taskStatusPanel, i18n.tasksFilterStatusCompleted());
		initCheckBox(taskStatusPanel, i18n.tasksFilterStatusDeleted());
	}
*/
	private void initCleaningStatusPanel() {
		Label title = new Label(i18n.roomStatusFilterRoomStatusTitle());
		title.addStyleName("dataGroupTitle");
		cleaningStatusPanel.add(title);

		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomDirty());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomClean());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomInspected());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOoo());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomOos());
		initCheckBox(cleaningStatusPanel, i18n.roomStatusFilterRoomShow());
	}

	private void initCheckBox(MaterialPanel cleaningStatusPanel, String title) {
		MaterialChip chip = new MaterialChip();
		chip.setText(title);
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setText(title);
		checkBox.setMarginTop(10);

		checkBox.addValueChangeHandler(e -> {
			chip.setVisible(e.getValue());
			getUiHandlers().filterChange();
		});

		cleaningStatusPanel.add(checkBox);
	}

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
		createOccupancyStatusLayout();

		createCleaningStatusLayout();
		setTaskKindLayout();
		roomNumberField.setGrid("s6 m4");
		controlPanel.add(roomNumberField);

		floorField.setGrid("s6 m4");
		controlPanel.add(floorField);

		roomTypeComboBox.setGrid("s6 m4");
		controlPanel.add(roomTypeComboBox);
	}

	private void createCleaningStatusLayout() {
		cleaningStatusPanel.setGrid("s6 m4");
//		cleaningStatusPanel.setBorderLeft("3px solid " + BlueThemeColors.C_PRIMARY);
		cleaningStatusPanel.addStyleName("dataGroupBox");
		controlPanel.add(cleaningStatusPanel);
	}

	private void createOccupancyStatusLayout() {
		taskStatusFilter.setGrid("s6 m4");
//		taskStatusPanel.setBorderLeft("3px solid " + BlueThemeColors.C_PRIMARY);
		taskStatusFilter.addStyleName("dataGroupBox");
		controlPanel.add(taskStatusFilter);
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
}
