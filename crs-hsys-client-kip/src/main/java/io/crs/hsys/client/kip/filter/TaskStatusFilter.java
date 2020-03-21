/**
 * 
 */
package io.crs.hsys.client.kip.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.filter.widget.BaseFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.cnst.TaskStatus;

/**
 * @author robi
 *
 */
public class TaskStatusFilter extends BaseFilter {
	private static Logger logger = Logger.getLogger(TaskStatusFilter.class.getName());

	private MaterialPanel panel;
	private Map<TaskStatus, MaterialCheckBox> checkBoxes = new HashMap<TaskStatus, MaterialCheckBox>();
	private final EventBus eventBus;
	private final KipMessages i18n;
	private final CoreConstants cnst;

	@Inject
	TaskStatusFilter(EventBus eventBus, KipMessages i18n, CoreConstants cnst) {
		logger.info("TaskStatusFilter()");
		this.eventBus = eventBus;
		this.i18n = i18n;
		this.cnst = cnst;

		panel = new MaterialPanel();
		initPanel();
		initWidget(panel);
	}

	private void initPanel() {
		panel.clear();
		panel.addStyleName("dataGroupBox");
		
		// hogy mobil nézet esetén a következő elem (a tisztasági státuszok panel)
		// megfelelő helyen helyezkedjen el
		panel.setMarginBottom(10);
		
		Label title = new Label(i18n.tasksFilterTaskStatusTitle());
		title.addStyleName("dataGroupTitle");
		panel.add(title);

		for (TaskStatus status : TaskStatus.values()) {
			checkBoxes.put(status, createCheckBox(status));
		}
		
		this.setChipLetter("fs");
		this.setChipLetterBackgroundColor(Color.RED);
		this.setChipLetterColor(Color.WHITE);
	}

	private MaterialCheckBox createCheckBox(TaskStatus status) {
		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setText(cnst.taskStatusMap().get(status.toString()));
		checkBox.setMarginTop(10);

		checkBox.addValueChangeHandler(e -> {
			refreshChip();
			eventBus.fireEvent(new FilterChangeEvent(DataTable.QUOTATION));
		});

		panel.add(checkBox);
		return checkBox;
	}

	private void refreshChip() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<TaskStatus, MaterialCheckBox> scb : checkBoxes.entrySet()) {
			if (scb.getValue().getValue()) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(cnst.taskStatusMap().get(scb.getKey().toString()).substring(0,1));
			}
		}
		setChipText(sb.toString());
	}

	@Override
	protected MaterialWidget getFilterWidget() {
		return panel;
	}

	public List<TaskStatus> getSelectedTaskStatuses() {
		List<TaskStatus> result = new ArrayList<TaskStatus>();
		for (Map.Entry<TaskStatus, MaterialCheckBox> scb : checkBoxes.entrySet()) {
			if (scb.getValue().getValue())
				result.add(scb.getKey());
		}
		return result;
	}
}
