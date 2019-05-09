/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialPanel;

import io.crs.hsys.client.core.filter.BaseFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.cnst.TaskStatus;

/**
 * @author robi
 *
 */
public class TaskStatusFilter extends BaseFilter {
	private static Logger logger = Logger.getLogger(TaskStatusFilter.class.getName());

	private MaterialPanel taskStatusPanel;
	private Map<TaskStatus, MaterialCheckBox> checkBoxes = new HashMap<TaskStatus, MaterialCheckBox>();
	private final KipMessages i18n;
	private final CoreConstants cnst;

	@Inject
	TaskStatusFilter(EventBus eventBus, KipMessages i18n, CoreConstants cnst) {
		super(eventBus);
		logger.info("ComboBoxFilter()");
		this.i18n = i18n;
		this.cnst = cnst;

		taskStatusPanel = new MaterialPanel();
		initTaskStatusPanel();
		initWidget(taskStatusPanel);
	}

	private void initTaskStatusPanel() {
		taskStatusPanel.clear();
		Label title = new Label(i18n.tasksFilterTaskStatusTitle());
		title.addStyleName("dataGroupTitle");
		taskStatusPanel.add(title);

		for (TaskStatus status : TaskStatus.values()) {
			checkBoxes.put(status, createCheckBox(status));
		}
	}

	private MaterialCheckBox createCheckBox(TaskStatus status) {
		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setText(cnst.taskStatusMap().get(status.toString()));
		checkBox.setMarginTop(10);

		checkBox.addValueChangeHandler(e -> {
			refreshChip();
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		taskStatusPanel.add(checkBox);
		return checkBox;
	}

	private void refreshChip() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<TaskStatus, MaterialCheckBox> scb : checkBoxes.entrySet()) {
			if (scb.getValue().getValue()) {
				if (sb.length() > 0)
					sb.append(",");
				sb.append(cnst.taskStatusMap().get(scb.getKey().toString()));
			}
		}
		setChipText(sb.toString());
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return taskStatusPanel;
	}
}
