/**
 * 
 */
package io.crs.hsys.client.cfg.editor.quotation;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

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
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.doc.QuotationDto;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;
import io.crs.hsys.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class QuotationEditorView extends ViewWithUiHandlers<QuotationEditorUiHandlers>
		implements QuotationEditorPresenter.MyView, Editor<QuotationDto> {
	private static Logger logger = Logger.getLogger(QuotationEditorView.class.getName());

	interface Binder extends UiBinder<Widget, QuotationEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<QuotationDto, QuotationEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox code, description;

	@Ignore
	@UiField
	MaterialComboBox<OrganizationDtor> organizationCombo;
	TakesValueEditor<OrganizationDtor> organization;

	@Ignore
	@UiField
	MaterialComboBox<QuotationStatusDto> statusCombo;
	TakesValueEditor<QuotationStatusDto> status;

	@Ignore
	@UiField
	MaterialComboBox<AppUserDtor> issuedByCombo;
	TakesValueEditor<AppUserDtor> issuedBy;

	@UiField
	MaterialDatePicker issueDate;

	/**
	* 
	*/
	@Inject
	QuotationEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst, CurrentUser user) {
		logger.info("QuotationEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		initQuotationStatusCombo();
		initOrganizationCombo();
		initIssuedCombo();
		initPostingDate(user.getLocale());

		this.driver = driver;

		driver.initialize(this);
	}

	private void initQuotationStatusCombo() {

		status = TakesValueEditor.of(new TakesValue<QuotationStatusDto>() {
			@Override
			public void setValue(QuotationStatusDto value) {
				statusCombo.setSingleValue(value);
			}

			@Override
			public QuotationStatusDto getValue() {
				return statusCombo.getSingleValue();
			}
		});
	}

	@Override
	public void setQuotationStatusData(List<QuotationStatusDto> data) {
		statusCombo.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		for (QuotationStatusDto status : data) {
			statusCombo.addItem(status.getCode() + " - " + status.getDescription(), status);
		}
		statusCombo.unselect();
	}

	private void initOrganizationCombo() {
		organization = TakesValueEditor.of(new TakesValue<OrganizationDtor>() {
			@Override
			public void setValue(OrganizationDtor value) {
				organizationCombo.setSingleValue(value);
			}

			@Override
			public OrganizationDtor getValue() {
				return organizationCombo.getSingleValue();
			}
		});
	}

	@Override
	public void setOrganizationData(List<OrganizationDtor> data) {
		logger.info("QuotationEditorView().setOrganizationData()");
		organizationCombo.clear();
		if ((data == null) || (data.isEmpty()))
			return;

		for (OrganizationDtor org : data) {
			logger.info("QuotationEditorView().setOrganizationData()->code=" + org.getCode());
			organizationCombo.addItem(org.getCode() + " - " + org.getName(), org);
		}
		organizationCombo.unselect();
	}

	private void initIssuedCombo() {
		issuedBy = TakesValueEditor.of(new TakesValue<AppUserDtor>() {
			@Override
			public void setValue(AppUserDtor value) {
				issuedByCombo.setSingleValue(value);
			}

			@Override
			public AppUserDtor getValue() {
				return issuedByCombo.getSingleValue();
			}
		});
	}

	@Override
	public void setIssuedByData(List<AppUserDtor> users) {
		logger.info("QuotationEditorView().setIssuedByData()");
		issuedByCombo.clear();
		if ((users == null) || (users.isEmpty()))
			return;

		for (AppUserDtor user : users) {
			logger.info("QuotationEditorView().setIssuedByData()->code=" + user.getCode());
			issuedByCombo.addItem(user.getCode() + " - " + user.getName(), user);
		}
		issuedByCombo.unselect();
	}

	private void initPostingDate(String locale) {
		if (locale.startsWith("hu"))
			issueDate.setLanguage(DatePickerLanguage.HU);
	}

	@Override
	public void edit(QuotationDto dto) {
		code.setReadOnly(dto.getId() != null);

		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				if (dto.getId() == null)
					code.setFocus(true);
				else
					description.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		QuotationDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show(QuotationDto dto) {
		// TODO Auto-generated method stub
	}
}
