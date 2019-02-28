/**
 * 
 */
package io.crs.hsys.client.kip.filter.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.filter.taskgroup.AbstractTaskGroupFilterView;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class TaskTodoFilterView extends AbstractTaskGroupFilterView implements TaskTodoFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTodoFilterView.class.getName());

	public static final String ALL_ITEMS = "*MIND";

	private MaterialComboBox<TaskGroupDto> taskGroupComboBox;

	@Inject
	TaskTodoFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("TaskTodoFilterView()");
		initTaskGroupFilter();
	}

	@Override
	protected void initView() {
		super.initView();
		taskGroupComboBox = new MaterialComboBox<TaskGroupDto>();
	}

	private void initTaskGroupFilter() {
		MaterialChip chip = new MaterialChip();
		chip.setVisible(false);
		collapsibleHeader.insert(chip, 1);

		taskGroupComboBox.setMarginTop(30);
		taskGroupComboBox.setLabel(i18nCore.taskTodoFilterTaskGroupLabel());
		taskGroupComboBox.setPlaceholder(i18nCore.taskTodoFilterTaskGroupPlaceholder());

		taskGroupComboBox.addSelectionHandler(e -> {
			setTaskGroupChip(chip, e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setTaskGroupChip(MaterialChip chip, TaskGroupDto taskGroup) {
		Boolean visible = ((taskGroup != null) && (!taskGroup.getCode().equals(ALL_ITEMS)));
		chip.setVisible(visible);
		if (visible)
			chip.setText(taskGroup.getCode());
	}

	@Override
	protected void createLayout() {
		setTaskKindLayout();
		taskGroupComboBox.setGrid("s12 m6");
		controlPanel.add(taskGroupComboBox);
		setOnlyActiveLayour();
	}

	@Override
	public void setTaskGroupData(List<TaskGroupDto> data) {
		taskGroupComboBox.clear();
		taskGroupComboBox.addItem("Mind", new TaskGroupDto(ALL_ITEMS, "Mind"));
		for (TaskGroupDto tg : data) {
			taskGroupComboBox.addItem(tg.getCode() + "-" + tg.getDescription(), tg);
		}
	}

	@Override
	public TaskGroupDto getSelectedTaskGroup() {
		return taskGroupComboBox.getSelectedValue().get(0);
	}
}
