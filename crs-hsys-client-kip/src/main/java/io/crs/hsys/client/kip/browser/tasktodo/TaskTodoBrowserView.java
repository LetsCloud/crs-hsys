/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageButtonConfig;
import io.crs.hsys.client.core.message.MessageButtonType;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public abstract class TaskTodoBrowserView extends ViewWithUiHandlers<TaskTodoBrowserUiHandlers>
		implements TaskTodoBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserView.class.getName());

	private final AbstractBrowserView<TaskTodoDto> browserView;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	public TaskTodoBrowserView(AbstractBrowserView<TaskTodoDto> table, CoreMessages i18nCore) {
		logger.info("TaskTodoBrowserView()");
		initWidget(table);

		this.browserView = table;
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

		browserView.setTableTitle(i18nCore.taskTodoBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		browserView.getDeleteIcon().addClickHandler(e -> {
			showMessage(createDeleteMessage());
		});

		// Kind Column
		browserView.addColumn(new ActionColumn<TaskTodoDto>() {
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
		browserView.addColumn(new DataColumn<TaskTodoDto>((object) -> {
			if (object.getTaskGroup() != null) {
				return object.getTaskGroup().getDescription();
			} else {
				return null;
			}
		}), i18nCore.taskTypeBrowserGroupColumn());

		// Description Column
		browserView.addColumn(
				new DataColumn<TaskTodoDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.taskTodoBrowserDescriptionColumn());

		// Time Column
//		table.addColumn(
//				new DataColumn<TaskTodoDto>((object) -> object.getTimeRequired().toString(),
//						(o1, o2) -> o1.getData().getTimeRequired().compareTo(o2.getData().getTimeRequired())),
//				i18nCore.taskTodoBrowserTimeReqColumn());
		browserView.addColumn(new DataColumn<TaskTodoDto>((object) -> object.getTimeRequired().toString()),
				i18nCore.taskTodoBrowserTimeReqColumn());

		// Active Column
		browserView.addColumn(new DataColumn<TaskTodoDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.comActive();
			} else {
				return i18nCore.comInactive();
			}
		}), i18nCore.comActive());

		// Edit Column
		browserView.addColumn(new ActionColumn<TaskTodoDto>((object) -> getUiHandlers().edit(object)) {
			@Override
			protected Boolean isVisible(TaskTodoDto object) {
				return isEnabledTaskKind(object.getKind());
			}
		});
	}

	@Override
	public void setData(List<TaskTodoDto> data) {
		browserView.setData(data);
	}

	protected abstract Boolean isEnabledTaskKind(TaskKind kind);

	@Override
	public void showMessage(MessageData message) {
		browserView.showMessage(message);
	}

	private String getSelectedCodes(List<TaskTodoDto> dtos) {
		StringBuilder temp = new StringBuilder();
		for (TaskTodoDto dto : dtos) {
			if (temp.length() > 0)
				temp.append(",");
			temp.append(dto.getDescription());
		}
		return temp.toString();
	}

	private MessageData createDeleteMessage() {
		MessageData message = new MessageData(i18nCore.taskTodoBrowserDeleteTitle(),
				i18nCore.taskTodoBrowserDeleteMessage(getSelectedCodes(browserView.getSelectedItems())));

		MessageButtonConfig yesButton = new MessageButtonConfig(MessageButtonType.YES, e -> {
			getUiHandlers().delete(browserView.getSelectedItems());
		});
		message.addBttonConfig(yesButton);

		MessageButtonConfig noButton = new MessageButtonConfig(MessageButtonType.NO, e -> {
		});
		message.addBttonConfig(noButton);

		return message;
	}
}
