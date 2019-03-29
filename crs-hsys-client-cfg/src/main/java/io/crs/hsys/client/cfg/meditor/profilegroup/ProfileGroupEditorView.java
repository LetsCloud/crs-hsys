package io.crs.hsys.client.cfg.meditor.profilegroup;

import java.util.Arrays;
import java.util.Map;
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
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import io.crs.hsys.client.cfg.i18n.CfgMessages;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.ProfileType;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

public class ProfileGroupEditorView extends ViewWithUiHandlers<ProfileGroupEditorUiHandlers>
		implements ProfileGroupEditorPresenter.MyView, Editor<ProfileGroupDto> {
	private static Logger logger = Logger.getLogger(ProfileGroupEditorView.class.getName());

	interface Binder extends UiBinder<Widget, ProfileGroupEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<ProfileGroupDto, ProfileGroupEditorView> {
	}

	private final Driver driver;

//private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialDialog modal;

	@Ignore
	@UiField
	MaterialTitle title;

	@UiField
	MaterialTextBox code, description;

	@UiField
	@Ignore
	MaterialComboBox<ProfileType> typeCombo;
	TakesValueEditor<ProfileType> type;

	@UiField
	MaterialCheckBox active;

	@UiField
	MaterialButton saveButton;

	private final CfgMessages i18n;

	/**
	* 
	*/
	@Inject
	ProfileGroupEditorView(Binder uiBinder, Driver driver, CfgMessages i18n, CoreConstants i18nCoreCnst) {
		logger.info("ProfileGroupEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

//saveButton.setBackgroundColor(Color.GREY);

		this.driver = driver;
		this.i18n = i18n;

		initProfileTypeCombo(i18nCoreCnst.profileTypeMap());

		driver.initialize(this);
	}

	private void initProfileTypeCombo(Map<String, String> profileTypeMap) {
		Arrays.asList(ProfileType.values()).forEach(st -> typeCombo.addItem(profileTypeMap.get(st.toString()), st));

		type = TakesValueEditor.of(new TakesValue<ProfileType>() {

			@Override
			public void setValue(ProfileType value) {
				typeCombo.setSingleValue(value);
			}

			@Override
			public ProfileType getValue() {
				return typeCombo.getSingleValue();
			}
		});
	}

	@Override
	public void open(ProfileGroupDto dto) {
		logger.info("ProfileGroupEditorView(.open())");

		if (dto.getId() == null) {
			title.setTitle(i18n.profileGroupCreateTitle());
		} else {
			title.setTitle(i18n.profileGroupEditTitle());
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
