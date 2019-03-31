/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import java.util.logging.Logger;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.dialog.MessageDialogWidget;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class TaskGroupEditorView extends ViewWithUiHandlers<TaskGroupEditorUiHandlers>
		implements TaskGroupEditorPresenter.MyView, Editor<TaskGroupDto> {
	private static Logger logger = Logger.getLogger(TaskGroupEditorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskGroupEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskGroupDto, TaskGroupEditorView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	@Ignore
	MaterialComboBox<TaskKind> kindCombo;
	TakesValueEditor<TaskKind> kind;

	@UiField
	@Ignore
	MaterialTitle title;

	@UiField
	MaterialTextBox code, description;

	@UiField
	MaterialCheckBox active;

	@UiField
	@Ignore
	MessageDialogWidget messageDialog;

	private final Driver driver;
	private final CoreMessages i18n;

	TaskGroupEditorView(Binder uiBinder, Driver driver, CoreMessages i18n) {
		logger.info("TaskGroupEditorView()");
		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18n = i18n;

		kind = TakesValueEditor.of(new TakesValue<TaskKind>() {

			@Override
			public void setValue(TaskKind value) {
				kindCombo.setSingleValue(value);
			}

			@Override
			public TaskKind getValue() {
				return kindCombo.getSingleValue();
			}
		});

		driver.initialize(this);
	}

	@Ignore
	protected MaterialComboBox<TaskKind> getKindCombo() {
		return kindCombo;
	}

	@Override
	public void open(Boolean isNew, TaskGroupDto dto) {
		if (isNew) {
			title.setTitle(i18n.taskGroupEditorCreateTitle());
		} else {
			title.setTitle(i18n.taskGroupEditorModifyTitle());
		}
		driver.edit(dto);

		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(500);

	}

	@Override
	public void close() {
		modal.close();
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		close();
	}

	@Override
	public void showMessage(MessageData message) {
		logger.info("TaskGroupEditorView().showMessage()");
		messageDialog.showMessage(message);
	}
}