/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile.contact;

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
import io.crs.hsys.client.cfg.editor.profile.ProfileLinkListEditor;
import io.crs.hsys.client.cfg.editor.profile.WebPresenceListEditor;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.ContactDto;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ContactEditorView extends ViewWithUiHandlers<ContactEditorUiHandlers>
		implements ContactEditorPresenter.MyView, Editor<ContactDto> {
	private static Logger logger = Logger.getLogger(ContactEditorView.class.getName());

	interface Binder extends UiBinder<Widget, ContactEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<ContactDto, ContactEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox name;

	@Ignore
	@UiField
	MaterialComboBoxAdd<ProfileGroupDto> profileGroupCombo;
	TakesValueEditor<ProfileGroupDto> profileGroup;

	@UiField(provided = true)
	ProfileLinkListEditor profileLinks;

	@UiField(provided = true)
	CommunicationListEditor communications;

	@UiField(provided = true)
	AddressListEditor addresses;

	@UiField(provided = true)
	WebPresenceListEditor webPresences;

	@Ignore
	@UiField
	MaterialIcon addCommunication, addAddress, addWebPresence, addProfileLink;

	@Ignore
	@UiField
	MaterialAnchorButton editButton, saveButton, cancelButton, deleteButton;

	private final EventBus eventBus;

	/**
	* 
	*/
	@Inject
	ContactEditorView(Binder uiBinder, EventBus eventBus, Driver driver, CommunicationListEditor communications,
			AddressListEditor addresses, WebPresenceListEditor webPresences, ProfileLinkListEditor profileLinks) {
		logger.info("ContactEditorView()");

		this.eventBus = eventBus;
		this.communications = communications;
		this.addresses = addresses;
		this.webPresences = webPresences;
		this.profileLinks = profileLinks;

		initWidget(uiBinder.createAndBindUi(this));

		initProfileGroupCombo();

		this.driver = driver;
		driver.initialize(this);
	}

	private void initProfileGroupCombo() {
		profileGroup = TakesValueEditor.of(new TakesValue<ProfileGroupDto>() {

			@Override
			public void setValue(ProfileGroupDto value) {
				logger.info("OrganizationEditorView().profileGroup.setValue(" + value + ")");
				profileGroupCombo.setSingleValue(value);
			}

			@Override
			public ProfileGroupDto getValue() {
				logger.info("OrganizationEditorView().profileGroup.getValue()" + profileGroupCombo.getSingleValue());
				return profileGroupCombo.getSingleValue();
			}
		});
	}

	@Override
	public void show(ContactDto dto) {
		setReadOnly(true);
		driver.edit(dto);
	}

	@Override
	public void edit(ContactDto dto) {
		setReadOnly(false);
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				name.setFocus(true);
//		profileGroupCombo.setFocus(true);
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
		this.getUiHandlers().edit(name.getValue());
		eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.OPEN, -1));
		setReadOnly(false);
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		ContactDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		this.getUiHandlers().cancel(name.getValue());
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

	@UiHandler("addProfileLink")
	public void onAddProfileLinkClick(ClickEvent event) {
		profileLinks.addItem();
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
		name.setReadOnly(readOnly);
		profileGroupCombo.setReadOnly(readOnly);

		addCommunication.setVisible(!readOnly);
		communications.setReadOnly(readOnly);

		addAddress.setVisible(!readOnly);
		addresses.setReadOnly(readOnly);

		addWebPresence.setVisible(!readOnly);
		webPresences.setReadOnly(readOnly);

		addProfileLink.setVisible(!readOnly);
		profileLinks.setReadOnly(readOnly);

		editButton.setVisible(readOnly);
		deleteButton.setVisible(readOnly);

		saveButton.setVisible(!readOnly);
		cancelButton.setVisible(!readOnly);

	}
}
