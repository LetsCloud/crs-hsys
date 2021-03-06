/**
 * 
 */
package io.crs.hsys.client.kip.editor.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.timepicker.MaterialTimePicker;
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTab;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.html.OptGroup;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskEditorView extends ViewWithUiHandlers<TaskEditorUiHandlers>
		implements TaskEditorPresenter.MyView, Editor<TaskDto> {
	private static Logger logger = Logger.getLogger(TaskEditorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskDto, TaskEditorView> {
	}

	private final Driver driver;

//private final CoreConstants i18nCoreCnst;

	@Ignore
	@UiField
	MaterialLink housekeepingKindLink, maintenanceKindLink;

	@Ignore
	@UiField
	MaterialTab taskKindTab;
	TakesValueEditor<TaskKind> kind;

	@Ignore
	@UiField
	MaterialCollapsible todosCollapsible;

	@Ignore
	@UiField
	MaterialCollapsibleItem<String> todosItem;

	@Ignore
	@UiField
	MaterialIcon expandTodosIcon, collapseTodosIcon;

	@Ignore
	@UiField
	MaterialCollapsibleBody todosBody;

	@Ignore
	@UiField
	MaterialComboBox<TaskTypeDto> taskTypeComboBox;
	TakesValueEditor<TaskTypeDto> type;

	@UiField
	MaterialTextArea description;

	@Ignore
	@UiField
	MaterialComboBox<AppUserDtor> assigneeComboBox;
	TakesValueEditor<AppUserDtor> assignee;

	@Ignore
	@UiField
	MaterialComboBox<RoomDto> roomComboBox;
	TakesValueEditor<RoomDto> room;

	@Ignore
	@UiField
	MaterialDatePicker dueDatePicker;

	@Ignore
	@UiField
	MaterialTimePicker dueTimePicker;

	TakesValueEditor<Date> dueDate;

	private Map<String, Long> avwEventsMap = new HashMap<String, Long>();

	/**
	* 
	*/
	@Inject
	TaskEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst, CurrentUser user) {
		logger.info("TaskEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		initTaskKind();
		initTaskTypeCombo();
		initAsigneeCombo();
		initRoomCombo();
		initDueDate(user.getLocale());

		this.driver = driver;

		dueDatePicker.addOpenHandler(avw -> {
			logger.info(avw.getTarget().getId() + " opened");
			avwEventsMap.put(avw.getTarget().getId(), new Date().getTime());
		});

		dueDatePicker.addCloseHandler(avw -> {
			logger.info(avw.getTarget().getId() + " closed");
			Long ts = avwEventsMap.get(avw.getTarget().getId());
			if (ts != null) {
				long elapsed = new Date().getTime() - ts;
				logger.info(avw.getTarget().getId() + "open / close interval: " + elapsed + "ms");
				if (elapsed < 500) {
					avw.getTarget().open();
				}
			}
		});

		driver.initialize(this);
	}

	private void initTaskKind() {
		kind = TakesValueEditor.of(new TakesValue<TaskKind>() {

			@Override
			public void setValue(TaskKind value) {
				if ((value == null) || (value.equals(TaskKind.TK_CLEANING))) {
					taskKindTab.setTabIndex(0);
					return;
				}
				taskKindTab.setTabIndex(1);
			}

			@Override
			public TaskKind getValue() {
				if (taskKindTab.getTabIndex() == 0)
					return TaskKind.TK_CLEANING;
				return TaskKind.TK_MAINTENANCE;
			}
		});
	}

	@UiHandler("housekeepingKindLink")
	public void onHousekeeipingKindClick(ClickEvent event) {
		getUiHandlers().filterTaskTypes(TaskKind.TK_CLEANING);
	}

	@UiHandler("maintenanceKindLink")
	public void onMaintenanceKindClick(ClickEvent event) {
		getUiHandlers().filterTaskTypes(TaskKind.TK_MAINTENANCE);
	}

	private void initTaskTypeCombo() {

		taskTypeComboBox.addValueChangeHandler(new ValueChangeHandler<List<TaskTypeDto>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<TaskTypeDto>> event) {
				loadTodos(taskTypeComboBox.getSingleValue());
			}
		});

		type = TakesValueEditor.of(new TakesValue<TaskTypeDto>() {
			@Override
			public void setValue(TaskTypeDto value) {
				taskTypeComboBox.setSingleValue(value);
			}

			@Override
			public TaskTypeDto getValue() {
				return taskTypeComboBox.getSingleValue();
			}
		});

		todosItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (todosBody.isVisible()) {
					adjustTodosIcons(false);
					return;
				}
				adjustTodosIcons(true);
			}
		});
	}

	@Override
	public void setTaskTypeData(List<TaskTypeDto> data) {
		taskTypeComboBox.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		data.sort((TaskTypeDto o1, TaskTypeDto o2) -> {
			int value = o1.getTaskGroup().getCode().compareTo(o2.getTaskGroup().getCode());
			if (value == 0) {
				return o1.getCode().compareTo(o2.getCode());
			}
			return value;
		});

		String groupCode = data.get(0).getTaskGroup().getCode();
		OptGroup optGroup = new OptGroup(data.get(0).getTaskGroup().getDescription());
		for (TaskTypeDto type : data) {
			if (!type.getTaskGroup().getCode().equals(groupCode)) {
				taskTypeComboBox.addGroup(optGroup);
				groupCode = type.getTaskGroup().getCode();
				optGroup = new OptGroup(type.getTaskGroup().getDescription());
			}
			taskTypeComboBox.addItem(type.getCode() + " - " + type.getDescription(), type, optGroup);
		}
		taskTypeComboBox.addGroup(optGroup);
		taskTypeComboBox.unselect();
	}

	private void loadTodos(TaskTypeDto type) {
		todosCollapsible.setVisible(false);
		if (type == null)
			return;
		if ((type.getTodos() == null) || (type.getTodos().isEmpty()))
			return;

		for (TaskTodoDto todo : type.getTodos()) {
			todosBody.add(new MaterialLabel(todo.getDescription()));
		}
		todosCollapsible.setVisible(true);
		todosItem.expand();
		adjustTodosIcons(true);
	}

	private void adjustTodosIcons(Boolean expand) {
		expandTodosIcon.setVisible(!expand);
		collapseTodosIcon.setVisible(expand);
	}

	private void initAsigneeCombo() {
		assignee = TakesValueEditor.of(new TakesValue<AppUserDtor>() {
			@Override
			public void setValue(AppUserDtor value) {
				assigneeComboBox.setSingleValue(value);
			}

			@Override
			public AppUserDtor getValue() {
				return assigneeComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void setAppUserData(List<AppUserDtor> data) {
		assigneeComboBox.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		for (AppUserDtor user : data) {
			assigneeComboBox.addItem(user.getCode() + " - " + user.getName(), user);
		}
		assigneeComboBox.unselect();
	}

	private void initRoomCombo() {
		room = TakesValueEditor.of(new TakesValue<RoomDto>() {
			@Override
			public void setValue(RoomDto value) {
				roomComboBox.setSingleValue(value);
			}

			@Override
			public RoomDto getValue() {
				return roomComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void setRoomData(List<RoomDto> data) {
		roomComboBox.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		data.sort((RoomDto o1, RoomDto o2) -> {
			int value = o1.getRoomType().getCode().compareTo(o2.getRoomType().getCode());
			if (value == 0) {
				return o1.getCode().compareTo(o2.getCode());
			}
			return value;
		});

		String typeCode = data.get(0).getRoomType().getCode();
		OptGroup optGroup = new OptGroup(typeCode);
		for (RoomDto room : data) {
			if (!room.getRoomType().getCode().equals(typeCode)) {
				roomComboBox.addGroup(optGroup);
				typeCode = room.getRoomType().getCode();
				optGroup = new OptGroup(typeCode);
			}
			roomComboBox.addItem(room.getCode() + " - " + room.getDescription(), room, optGroup);
		}
		roomComboBox.addGroup(optGroup);
		roomComboBox.unselect();
	}

	private void initDueDate(String locale) {
		if (locale.startsWith("hu"))
			dueDatePicker.setLanguage(DatePickerLanguage.HU);

		dueDate = TakesValueEditor.of(new TakesValue<Date>() {

			@Override
			public void setValue(Date value) {
				if (value == null)
					return;
				dueDatePicker.setValue(value);
				dueTimePicker.setValue(value);
			}

			@Override
			public Date getValue() {
				Date date = DateUtils.mergeDateAndTime(dueDatePicker.getValue(), dueTimePicker.getValue());
				return date;
			}
		});
	}

	@Override
	public void show(TaskDto dto) {
	}

	@Override
	public void edit(TaskDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				description.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
//TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		TaskDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void close() {
//TODO Auto-generated method stub

	}
}
