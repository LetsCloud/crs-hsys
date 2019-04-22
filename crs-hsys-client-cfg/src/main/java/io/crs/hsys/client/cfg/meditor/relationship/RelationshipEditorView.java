/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.relationship;

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

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.dialog.MessageDialogWidget;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipEditorView extends ViewWithUiHandlers<RelationshipEditorUiHandlers>
		implements RelationshipEditorPresenter.MyView, Editor<RelationshipDto> {
	private static Logger logger = Logger.getLogger(RelationshipEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RelationshipEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RelationshipDto, RelationshipEditorView> {
	}

	private final Driver driver;

//private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialDialog modal;

	@Ignore
	@UiField
	MaterialTitle title;

	@UiField
	MaterialTextBox forward, reverse;

	@UiField
	MaterialCheckBox active;

	@UiField
	MaterialButton saveButton;

	@UiField
	@Ignore
	MessageDialogWidget messageDialog;

	private final CoreMessages i18n;

	/**
	* 
	*/
	@Inject
	RelationshipEditorView(Binder uiBinder, Driver driver, CoreMessages i18n, CoreConstants i18nCoreCnst) {
		logger.info("RelationshipEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18n = i18n;

		driver.initialize(this);
	}

	@Override
	public void open(RelationshipDto dto) {
		if (dto.getId() == null) {
			title.setTitle(i18n.relationshipCreateTitle());
		} else {
			title.setTitle(i18n.relationshipEditTitle());
		}

		driver.edit(dto);

		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				forward.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void close() {
		modal.close();
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
//TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		logger.info("RelationshipEditorView().onSaveClick()");
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void showMessage(MessageData message) {
		messageDialog.showMessage(message);
	}
}
