/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.client.core.ui.browser.ActionColumn;
import io.crs.hsys.client.core.ui.browser.DataColumn;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public abstract class TaskTodoBrowserView extends ViewWithUiHandlers<TaskTodoBrowserUiHandlers>
		implements TaskTodoBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserView.class.getName());

	private final AbstractBrowserView<TaskTodoDto> table;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	public TaskTodoBrowserView(AbstractBrowserView<TaskTodoDto> table, CoreMessages i18nCore) {
		logger.info("TaskTodoBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(TaskTodoBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(TaskTodoBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();

	}

	@Override
	protected void onAttach() {
		super.onAttach();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.taskTodoBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Kind Column
		table.addColumn(new ActionColumn<TaskTodoDto>() {
			@Override
			protected MaterialIcon getIcon(TaskTodoDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getTaskIcon(object.getKind()));
				icon.setTextColor(RoomStatusUtils.getTaskColor(object.getKind()));
				return icon;
			}
		}, i18nCore.taskTodoBrowserKindColumn());

		// Group Column
		table.addColumn(new DataColumn<TaskTodoDto>((object) -> {
			if (object.getTaskGroup() != null) {
				return object.getTaskGroup().getDescription();
			} else {
				return null;
			}
		}), i18nCore.taskTypeBrowserGroupColumn());

		// Description Column
		table.addColumn(
				new DataColumn<TaskTodoDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.taskTodoBrowserDescriptionColumn());

		// Time Column
		table.addColumn(
				new DataColumn<TaskTodoDto>((object) -> object.getTimeRequired().toString(),
						(o1, o2) -> o1.getData().getTimeRequired().compareTo(o2.getData().getTimeRequired())),
				i18nCore.taskTodoBrowserTimeReqColumn());

		// Active Column
		table.addColumn(new DataColumn<TaskTodoDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.comActive();
			} else {
				return i18nCore.comInactive();
			}
		}), i18nCore.comActive());

		// Edit Column
		table.addColumn(new ActionColumn<TaskTodoDto>((object) -> getUiHandlers().edit(object)) {
			@Override
			protected Boolean isVisible(TaskTodoDto object) {
				return isEnabledTaskKind(object.getKind());
			}
		});
	}

	@Override
	public void setData(List<TaskTodoDto> data) {
		table.setData(data);
	}

	protected abstract Boolean isEnabledTaskKind(TaskKind kind);
}
