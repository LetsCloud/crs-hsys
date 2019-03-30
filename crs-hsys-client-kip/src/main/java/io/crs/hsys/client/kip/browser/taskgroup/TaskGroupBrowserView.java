/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

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
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public abstract class TaskGroupBrowserView extends ViewWithUiHandlers<TaskGroupBrowserUiHandlers>
		implements TaskGroupBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskGroupBrowserView.class.getName());

	private final AbstractBrowserView<TaskGroupDto> table;
	private final CoreMessages i18n;

	/**
	* 
	*/
	public TaskGroupBrowserView(AbstractBrowserView<TaskGroupDto> table, CoreMessages i18n) {
		logger.info("TaskGroupBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18n = i18n;

		bindSlot(TaskGroupBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(TaskGroupBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();

	}

	@Override
	protected void onAttach() {
		super.onAttach();
	}

	private void initTable() {

		table.setTableTitle(i18n.taskGroupBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			logger.info("TaskGroupBrowserView().onDeleteClick()");
			getUiHandlers().delete(table.getSelected());
		});

		// Kind Column
		table.addColumn(new ActionColumn<TaskGroupDto>() {
			@Override
			protected MaterialIcon getIcon(TaskGroupDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getTaskIcon(object.getKind()));
				icon.setTextColor(RoomStatusUtils.getTaskColor(object.getKind()));
//				icon.setCircle(true);
//				icon.setTextColor(Color.WHITE);
				// icon.setWidth("50px");
				// icon.setIconPosition(IconPosition.NONE);
				return icon;
			}
		}, i18n.taskGroupBrowserKindColumn());

		// Code Column
		table.addColumn(
				new DataColumn<TaskGroupDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18n.taskGroupBrowserCodeColumn());

		// Description Column
		table.addColumn(
				new DataColumn<TaskGroupDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18n.taskGroupBrowserDescriptionColumn());

		// Active Column
		table.addColumn(new DataColumn<TaskGroupDto>((object) -> {
			if (object.getActive()) {
				return i18n.comActive();
			} else {
				return i18n.comInactive();
			}
		}), i18n.comActive());

		// Edit Column
		table.addColumn(new ActionColumn<TaskGroupDto>((object) -> getUiHandlers().edit(object)) {
			@Override
			protected Boolean isVisible(TaskGroupDto object) {
				return isEnabledTaskKind(object.getKind());
			}
		});
	}

	@Override
	public void setData(List<TaskGroupDto> data) {
		table.setData(data);
	}

	protected abstract Boolean isEnabledTaskKind(TaskKind kind);
}
