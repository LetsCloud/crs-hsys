/**
 * 
 */
package io.crs.hsys.client.core.meditor.usergroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import gwt.material.design.client.ui.MaterialToast;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupEditorView extends ViewWithUiHandlers<UserGroupEditorUiHandlers>
		implements UserGroupEditorPresenter.MyView, Editor<UserGroupDto> {
	private static Logger logger = Logger.getLogger(UserGroupEditorView.class.getName());

	interface Binder extends UiBinder<Widget, UserGroupEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<UserGroupDto, UserGroupEditorView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	@Ignore
	MaterialTitle title;

	@UiField
	MaterialTextBox name;

	private final Driver driver;
	private final CoreMessages i18n;

	@Inject
	UserGroupEditorView(Binder uiBinder, Driver driver, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18n = i18n;

		driver.initialize(this);
	}

	@Override
	public void open(Boolean isNew, UserGroupDto dto) {
		logger.info("open()");
		if (isNew) {
			title.setTitle(i18n.userGroupEditorCreateTitle());
		} else {
			title.setTitle(i18n.userGroupEditorModifyTitle());
		}
		driver.edit(dto);
//		name.clearErrorOrSuccess();
		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				name.setFocus(true);
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
	public void displayError(EntityPropertyCode code, String message) {
		switch (code) {
		case NONE:
			break;
		case USER_GROUP_NAME:
	//		name.setError(message);
			break;
		}
		MaterialToast.fireToast(message);
	}
}