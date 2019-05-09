/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.Column;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.AbstractColumnConfig;
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

//	private static final int COL_KIND = 0;
//	private static final int COL_GROUP = 1;
//	private static final int COL_DESCRIPRION = 2;
//	private static final int COL_TIME = 3;
	private static final int COL_ACTIVE = 4;

	public class ColumnConfig extends AbstractColumnConfig<TaskTodoDto> {

		public ColumnConfig(Column<TaskTodoDto, ?> column) {
			super(column);
		}

		public ColumnConfig(String header, Column<TaskTodoDto, ?> column) {
			super(header, column);
		}
	}

	private final AbstractBrowserView<TaskTodoDto> browserView;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	public TaskTodoBrowserView(AbstractBrowserView<TaskTodoDto> browserView, CoreMessages i18nCore) {
		logger.info("TaskTodoBrowserView()");
		initWidget(browserView);

		this.browserView = browserView;
		this.i18nCore = i18nCore;

		bindSlot(TaskTodoBrowserPresenter.SLOT_FILTER, browserView.getFilterPanel());
		bindSlot(TaskTodoBrowserPresenter.SLOT_EDITOR, browserView.getEditorPanel());

		initBrowswerView();
	}

	private void initBrowswerView() {
		browserView.setTableTitle(i18nCore.taskTodoBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		browserView.getDeleteIcon().addClickHandler(e -> {
			showMessage(createDeleteMessage());
		});

		browserView.clearColumnConfigs();

		// 0 - Kind Column
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.taskTodoBrowserKindColumn(), createKindColumn()));

		// 1 - Group Column
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.taskTypeBrowserGroupColumn(), createGroupColumn()));

		// 2 - Description Column
		browserView.addColumnConfigs(
				new ColumnConfig(i18nCore.taskTodoBrowserDescriptionColumn(), createDescriptionColumn()));

		// 3 - Time Column
//		table.addColumn(
//				new DataColumn<TaskTodoDto>((object) -> object.getTimeRequired().toString(),
//						(o1, o2) -> o1.getData().getTimeRequired().compareTo(o2.getData().getTimeRequired())),
//				i18nCore.taskTodoBrowserTimeReqColumn());
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.taskTodoBrowserTimeReqColumn(), createTimeColumn()));

		// 4 - Active Column
		browserView.addColumnConfigs(new ColumnConfig(i18nCore.comActive(), createActiveColumn()));

		// 5 - Edit Column
		browserView.addColumnConfigs(new ColumnConfig(createEditActionColumn()));

		browserView.addAllColumns();
	}

	@Override
	public void reConfigColumns() {
//		browserView.hideColumn(COL_TIME, Window.getClientWidth() <= 1024);
		browserView.hideColumn(COL_ACTIVE, Window.getClientWidth() <= 1240);
	}

	private ActionColumn<TaskTodoDto> createKindColumn() {
		return new ActionColumn<TaskTodoDto>() {
			@Override
			protected MaterialIcon getIcon(TaskTodoDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getTaskIcon(object.getKind()));
				icon.setTextColor(RoomStatusUtils.getTaskColor(object.getKind()));
				return icon;
			}
		};
	}

	private DataColumn<TaskTodoDto> createGroupColumn() {
		return new DataColumn<TaskTodoDto>((object) -> {
			if (object.getTaskGroup() != null) {
				return object.getTaskGroup().getDescription();
			} else {
				return null;
			}
		});
	}

	private DataColumn<TaskTodoDto> createDescriptionColumn() {
		return new DataColumn<TaskTodoDto>((object) -> object.getDescription(),
				(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription()));
	}

	private DataColumn<TaskTodoDto> createTimeColumn() {
		return new DataColumn<TaskTodoDto>((object) -> object.getTimeRequired().toString());
	}

	private DataColumn<TaskTodoDto> createActiveColumn() {
		return new DataColumn<TaskTodoDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.comActive();
			} else {
				return i18nCore.comInactive();
			}
		});
	}

	private ActionColumn<TaskTodoDto> createEditActionColumn() {
		return new ActionColumn<TaskTodoDto>((object) -> getUiHandlers().edit(object)) {
			@Override
			protected Boolean isVisible(TaskTodoDto object) {
				return isEnabledTaskKind(object.getKind());
			}
		};
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
