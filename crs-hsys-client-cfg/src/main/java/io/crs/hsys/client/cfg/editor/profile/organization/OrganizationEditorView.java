/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.organization;

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
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addext.client.ui.MaterialComboBoxAdd;
import gwt.material.design.client.ui.MaterialAnchorButton;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.cfg.editor.profile.AddressListEditor;
import io.crs.hsys.client.cfg.editor.profile.CommunicationActionEvent;
import io.crs.hsys.client.cfg.editor.profile.CommunicationListEditor;
import io.crs.hsys.client.cfg.editor.profile.WebPresenceListEditor;
import io.crs.hsys.client.core.resources.ThemeParams;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.OrganizationDto;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class OrganizationEditorView extends ViewWithUiHandlers<OrganizationEditorUiHandlers>
		implements OrganizationEditorPresenter.MyView, Editor<OrganizationDto> {
	private static Logger logger = Logger.getLogger(OrganizationEditorView.class.getName());

	interface Binder extends UiBinder<Widget, OrganizationEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<OrganizationDto, OrganizationEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox code, name;

	@Ignore
	@UiField
	MaterialComboBoxAdd<ProfileGroupDto> profileGroupCombo;
	TakesValueEditor<ProfileGroupDto> profileGroup;

	@UiField(provided = true)
	CommunicationListEditor communications;

	@UiField(provided = true)
	AddressListEditor addresses;

	@UiField(provided = true)
	WebPresenceListEditor webPresences;
	
	@Ignore
	@UiField
	MaterialIcon addCommunication, addAddress, addWebPresence;

	@Ignore
	@UiField
	MaterialAnchorButton editButton, saveButton, cancelButton, deleteButton;

	private final EventBus eventBus;

	/**
	* 
	*/
	@Inject
	OrganizationEditorView(Binder uiBinder, EventBus eventBus, Driver driver, CommunicationListEditor communications,
			AddressListEditor addresses, WebPresenceListEditor webPresences, ThemeParams themeParams) {
		logger.info("OrganizationEditorView()");

		this.eventBus = eventBus;
		this.communications = communications;
		this.addresses = addresses;
		this.webPresences = webPresences;

		initWidget(uiBinder.createAndBindUi(this));

		initProfileGroupCombo();

		this.driver = driver;
		driver.initialize(this);
	}

	private void initProfileGroupCombo() {
		profileGroup = TakesValueEditor.of(new TakesValue<ProfileGroupDto>() {

			@Override
			public void setValue(ProfileGroupDto value) {
				profileGroupCombo.setSingleValue(value);
			}

			@Override
			public ProfileGroupDto getValue() {
				return profileGroupCombo.getSingleValue();
			}
		});

	}

	@Override
	public void show(OrganizationDto dto) {
		setReadOnly(true);
		driver.edit(dto);
	}

	@Override
	public void edit(OrganizationDto dto) {
		setReadOnly(false);
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				name.setFocus(true);
//				profileGroupCombo.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		// TODO Auto-generated method stub
	}

	@UiHandler("editButton")
	void onEditClick(ClickEvent event) {
		eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.OPEN, -1));
		setReadOnly(false);
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		OrganizationDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.CLOSE, -1));
		setReadOnly(true);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@UiHandler("addCommunication")
	public void onAddCommunicationClick(ClickEvent event) {
		communications.addItem();
	}

	@UiHandler("addAddress")
	public void onAddAddressClick(ClickEvent event) {
		addresses.addItem();
	}

	@UiHandler("addWebPresence")
	public void onWebPresenceClick(ClickEvent event) {
		webPresences.addItem();
	}

	@Override
	public void setProfileGroupData(List<ProfileGroupDto> profileGroupData) {
		profileGroupCombo.clear();
		for (ProfileGroupDto dto : profileGroupData) {
			profileGroupCombo.addItem(dto.getCode() + " - " + dto.getDescription(), dto);
		}
	}

	@Override
	public void setReadOnly(Boolean readOnly) {
		code.setReadOnly(readOnly);
		name.setReadOnly(readOnly);
		profileGroupCombo.setReadOnly(readOnly);

		addCommunication.setVisible(!readOnly);
		communications.setReadOnly(readOnly);
		
		addAddress.setVisible(!readOnly);
		addresses.setReadOnly(readOnly);

		addWebPresence.setVisible(!readOnly);
		webPresences.setReadOnly(readOnly);
		
		editButton.setVisible(readOnly);
		deleteButton.setVisible(readOnly);

		saveButton.setVisible(!readOnly);
		cancelButton.setVisible(!readOnly);

	}
}
