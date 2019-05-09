/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import java.util.List;
import java.util.logging.Logger;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.room.RoomBrowserPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageButtonConfig;
import io.crs.hsys.client.core.message.MessageButtonType;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public abstract class TaskTypeBrowserView extends ViewWithUiHandlers<TaskTypeBrowserUiHandlers>
		implements TaskTypeBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTypeBrowserView.class.getName());

	private final AbstractBrowserView<TaskTypeDto> browserView;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	TaskTypeBrowserView(AbstractBrowserView<TaskTypeDto> browserView, CoreMessages i18nCore) {
		logger.info("TaskTypeBrowserView()");
		initWidget(browserView);

		this.browserView = browserView;
		this.i18nCore = i18nCore;

		bindSlot(RoomBrowserPresenter.SLOT_FILTER, browserView.getFilterPanel());

		initBrowserView();
	}

	private void initBrowserView() {
		browserView.setTableTitle(i18nCore.taskTypeBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> getUiHandlers().addNew());
		browserView.getDeleteIcon().addClickHandler(e -> {
			showMessage(createDeleteMessage());
		});

		// Kind Column
		browserView.addColumn(new ActionColumn<TaskTypeDto>() {
			@Override
			protected MaterialIcon getIcon(TaskTypeDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getTaskIcon(object.getKind()));
				icon.setTextColor(RoomStatusUtils.getTaskColor(object.getKind()));
				return icon;
			}
		}, i18nCore.taskTypeBrowserKindColumn());

		// Group Column
		browserView.addColumn(new DataColumn<TaskTypeDto>((object) -> {
			if (object.getTaskGroup() != null) {
				return object.getTaskGroup().getDescription();
			} else {
				return null;
			}
		}), i18nCore.taskTypeBrowserGroupColumn());

		// Code Column
		browserView.addColumn(
				new DataColumn<TaskTypeDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.taskTypeBrowserCodeColumn());

		// Description Column
		browserView.addColumn(
				new DataColumn<TaskTypeDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.taskTypeBrowserDescriptionColumn());

		// Edit Column
		browserView.addColumn(new ActionColumn<TaskTypeDto>((object) -> getUiHandlers().edit(object)) {
			@Override
			protected Boolean isVisible(TaskTypeDto object) {
				return isEnabledTaskKind(object.getKind());
			}
		});
	}

	@Override
	public void setData(List<TaskTypeDto> data) {
		browserView.setData(data);
	}

	protected abstract Boolean isEnabledTaskKind(TaskKind kind);

	@Override
	public void showMessage(MessageData message) {
		browserView.showMessage(message);
	}

	private String getSelectedCodes(List<TaskTypeDto> dtos) {
		StringBuilder temp = new StringBuilder();
		for (TaskTypeDto dto : dtos) {
			if (temp.length() > 0)
				temp.append(",");
			temp.append(dto.getCode());
		}
		return temp.toString();
	}

	private MessageData createDeleteMessage() {
		MessageData message = new MessageData(i18nCore.taskGroupBrowserDeleteTitle(),
				i18nCore.taskGroupBrowserDeleteMessage(getSelectedCodes(browserView.getSelectedItems())));

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
