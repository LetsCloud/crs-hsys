/**
 * 
 */
package io.crs.hsys.client.cfg.meditor.qtnstatus;

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

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusEditorView extends ViewWithUiHandlers<QuotationStatusEditorUiHandlers>
		implements QuotationStatusEditorPresenter.MyView, Editor<QuotationStatusDto> {
	private static Logger logger = Logger.getLogger(QuotationStatusEditorView.class.getName());

	interface Binder extends UiBinder<Widget, QuotationStatusEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<QuotationStatusDto, QuotationStatusEditorView> {
	}

	private final Driver driver;
	private final CoreMessages i18nCore;

	@Ignore
	@UiField
	MaterialDialog modal;

	@Ignore
	@UiField
	MaterialTitle title;

	@UiField
	MaterialTextBox code, description;

	@UiField
	MaterialCheckBox active;

	/**
	* 
	*/
	@Inject
	QuotationStatusEditorView(Binder uiBinder, Driver driver, CoreMessages i18nCore, CoreConstants i18nCoreCnst) {
		logger.info("QuotationStatusEditorView()");
		initWidget(uiBinder.createAndBindUi(this));
		this.driver = driver;
		this.i18nCore = i18nCore;
		driver.initialize(this);
	}

	@Override
	public void open(QuotationStatusDto dto) {
		if (dto.getId() == null) {
			title.setTitle(i18nCore.quotationStatusCreateTitle());
		} else {
			title.setTitle(i18nCore.quotationStatusEditTitle());
		}

		driver.edit(dto);

		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
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
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}
}
